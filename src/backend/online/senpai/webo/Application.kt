package online.senpai.webo

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.sessions.*
import io.ktor.features.*
import org.slf4j.event.*
import com.fasterxml.jackson.databind.*
import io.ktor.auth.*
import io.ktor.html.*
import io.ktor.jackson.*
import io.ktor.server.netty.EngineMain
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.url
import kotlinx.html.*
import online.senpai.webo.dao.DaoFacade
import online.senpai.webo.exception.AuthenticationException
import online.senpai.webo.exception.AuthorizationException
import online.senpai.webo.module.daoModule
import online.senpai.webo.module.mysqlConnectionModule
import online.senpai.webo.module.serviceModule
import online.senpai.webo.router.dumbcord
import online.senpai.webo.router.kodebin
import online.senpai.webo.service.GithubApiV3
import online.senpai.webo.service.GithubApiV3User
import online.senpai.webo.session.AuthenticatedUser
import org.koin.Logger.slf4jLogger
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get as getDependency
import org.mpierce.ktor.csrf.CsrfProtection
import org.mpierce.ktor.csrf.HeaderPresent
import org.mpierce.ktor.csrf.OriginMatchesKnownHost
import java.io.File

fun main(args: Array<String>): Unit = EngineMain.main(args)

@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
fun Application.main() {
    install(Koin) {
        slf4jLogger()
        modules(
            listOf(
                serviceModule,
                mysqlConnectionModule,
                daoModule
            )
        )
    }

    /*install(CsrfProtection) {
        validate(OriginMatchesKnownHost("https", "senpai.online"))
        validate(HeaderPresent("X-Some-Custom-Header-Your-Frontend-Sends"))
    }*/

    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024)
        }
    }

    install(AutoHeadResponse)

    install(CallLogging) {
        level = if (isDev) Level.DEBUG else Level.INFO
        filter { call: ApplicationCall -> call.request.path().startsWith("/") }
    }

    install(ConditionalHeaders)

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        allowCredentials = true
    }

    /*install(CachingHeaders) {
        options { outgoingContent ->
            when (outgoingContent.contentType?.withoutParameters()) {
                ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60), expires = null as? GMTDate?)
                else -> null
            }
        }
    }*/

    install(DataConversion)
    install(XForwardedHeaderSupport)

    /*install(HSTS) {
        includeSubDomains = true
    }*/

    /*// https://ktor.io/servers/features/https-redirect.html#testing
    if (isDev) {
        install(HttpsRedirect) {
            // The port to redirect to. By default 443, the default HTTPS port.
            sslPort = 443
            // 301 Moved Permanently, or 302 Found redirect.
            permanentRedirect = true
        }
    }*/

    /*install(io.ktor.websocket.WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }*/

    /*install(PartialContent) {
        // Maximum number of ranges that will be accepted from a HTTP request.
        // If the HTTP request specifies more ranges, they will all be merged into a single range.
        maxRangeCount = 10
    }*/

    install(Sessions) {
        cookie<AuthenticatedUser>(
            name = "session_id",
            storage = directorySessionStorage(rootDir = File(".sessions"), cached = true)
        ) {
            cookie.path = "/"
            cookie.extensions["SameSite"] = "Strict"
        }
    }

    install(Authentication) {
        oauth("githubOauth") {
            client = getDependency()
            providerLookup = { GithubLoginProvider().getLoginProviderSettings(environment.config) }
            urlProvider = {
                url { path("user", "login") }
            }
            skipWhen { call: ApplicationCall -> call.sessions.get<AuthenticatedUser>() != null }
        }
        session<AuthenticatedUser> {
            challenge = SessionAuthChallenge.Unauthorized
            validate { principal: AuthenticatedUser -> principal } // TODO
        }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        if (isDev) trace { application.log.trace(it.buildText()) }
        authenticate("githubOauth") {
            route("/user/login/{type?}") {
                param("error") {
                    handle {
                        call.loginFailedPage(call.parameters.getAll("error").orEmpty())
                    }
                }
                handle {
                    val token: String = when (val principal: OAuthAccessTokenResponse = call.authentication.principal() ?: throw AuthenticationException()) {
                        is OAuthAccessTokenResponse.OAuth1a -> throw AuthenticationException() //  Not supported. TODO Should I handle this case?
                        is OAuthAccessTokenResponse.OAuth2 -> principal.accessToken
                    }
                    val githubApi: GithubApiV3 = this@route.getDependency()
                    val data: GithubApiV3User = githubApi.getUserData(token)
                    call.sessions.set(
                        AuthenticatedUser(
                            userId = data.id,
                            userName = data.name,
                            token = token
                        )
                    )
                    call.respondRedirect("/redirect?successful=true")
                }
            }
        }
        authenticate(optional = false) {
            route("/user") {
                route("info") {
                    get {
                        val principal: AuthenticatedUser? = call.authentication.principal()
                        if (principal != null) {
                            call.respondText("${principal.userId} ${principal.userName}")
                        } else {
                            call.respondText("NULL")
                        }
                    }
                }
            }
        }
        route("/user/test") {
            get {
            }
        }
        route("/kodebin") {
            kodebin()
        }
        route("/dumbcord") {
            dumbcord()
        }

        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized, cause)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden, cause)
            }
        }
    }

    getDependency<DaoFacade>().apply {
        environment.monitor.subscribe(ApplicationStopped) { close() }
        init()
    }
}

private suspend fun ApplicationCall.loginPage() {
    respondHtml {
        head {
            title { +"Login with" }
        }
        body {
            h1 {
                +"Login with:"
            }
            p {
                a(href = url { path("user", "login") }) {
                    +"GitHub OAuth"
                }
            }
        }
    }
}

private suspend fun ApplicationCall.loginFailedPage(errors: List<String>) {
    respondHtml {
        head {
            title { +"Login with" }
        }
        body {
            h1 {
                +"Login error"
            }

            for (e in errors) {
                p {
                    +e
                }
            }
        }
    }
}

private suspend fun ApplicationCall.loggedInSuccessResponse(data: GithubApiV3User) {
    respondHtml {
        head {
            title { +"Logged in" }
        }
        body {
            h1 {
                +"You are logged in"
            }
            p {
                +"Your logged in as $data"
            }
        }
    }
}

@KtorExperimentalAPI
val Application.isDev: Boolean
    get() = environment.config.property("ktor.environment").getString() == "dev"
