package online.senpai.webo

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import io.ktor.sessions.directorySessionStorage
import io.ktor.util.KtorExperimentalAPI
import online.senpai.webo.exception.AuthenticationException
import online.senpai.webo.exception.AuthorizationException
import online.senpai.webo.exception.BadRequest
import online.senpai.webo.module.dbConnectionModule
import online.senpai.webo.module.repositoryModule
import online.senpai.webo.module.serviceModule
import online.senpai.webo.router.evolveHandler
import online.senpai.webo.session.AuthenticatedUser
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger
import org.slf4j.event.Level
import java.io.File
import kotlin.collections.set

fun main(args: Array<String>): Unit = EngineMain.main(args)

@KtorExperimentalLocationsAPI
@KtorExperimentalAPI
@Suppress("unused") // Referenced in application.conf
fun Application.main() {
    install(Koin) {
        SLF4JLogger()
        modules(
            listOf(
                serviceModule,
                repositoryModule,
                dbConnectionModule
            )
        )
    }

    /*install(CsrfProtection) {
        validate(OriginMatchesKnownHost("https", "senpai.online"))
        validate(HeaderPresent("X-Some-Custom-Header-Your-Frontend-Sends"))
    }*/

    /*install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024)
        }
    }*/

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

    /*install(Authentication) {
        oauth("githubOauth") {
            client = getDependency()
            providerLookup = { GithubLoginProvider().getLoginProviderSettings(environment.config) }
            urlProvider = {
                url { path("user", "login") }
            }
            skipWhen { call: ApplicationCall -> call.sessions.get<AuthenticatedUser>() != null }
        }
        session<AuthenticatedUser> {
            *//*challenge = SessionAuthChallenge.Unauthorized*//*
            validate { principal: AuthenticatedUser -> principal } // TODO
        }
    }*/

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Locations)

    routing {
        if (isDev) trace { application.log.trace(it.buildText()) }
        route("/api") {
            route("/evolve") { // TODO
                evolveHandler()
            }
        }
        /*authenticate("githubOauth") {
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
        }*/

        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized, cause)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden, cause)
            }
            exception<BadRequest> { cause ->
                call.respond(HttpStatusCode.BadRequest /*cause*/)
            }
        }
    }

    /*val dataSource: HikariDataSource = getDependency<HikariDataSource>()
    environment.monitor.subscribe(ApplicationStopping) {
        dataSource.close()
    }
    Database.connect(dataSource)
    getDependency<OldEvolveRepository>().createTable()*/

    /*getDependency<DaoFacade>().apply {
        environment.monitor.subscribe(ApplicationStopped) { close() }
        init()
    }*/
}

@KtorExperimentalAPI
val Application.isDev: Boolean
    get() = environment.config.property("ktor.environment").getString() == "dev"
