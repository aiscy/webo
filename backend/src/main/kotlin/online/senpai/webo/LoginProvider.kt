package online.senpai.webo

import io.ktor.auth.OAuthServerSettings
import io.ktor.config.ApplicationConfig
import io.ktor.http.HttpMethod
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
sealed class LoginProvider {
    abstract val providerName: String
    abstract fun getLoginProviderSettings(config: ApplicationConfig) : OAuthServerSettings.OAuth2ServerSettings
}

@KtorExperimentalAPI
class GithubLoginProvider : LoginProvider() {
    override val providerName: String = "github"

    override fun getLoginProviderSettings(config: ApplicationConfig): OAuthServerSettings.OAuth2ServerSettings {
        return OAuthServerSettings.OAuth2ServerSettings(
            name = providerName,
            authorizeUrl = "https://github.com/login/oauth/authorize",
            accessTokenUrl = "https://github.com/login/oauth/access_token",
            clientId = config.property("ktor.auth.oauth.github.clientId").getString(),
            clientSecret = config.property("ktor.auth.oauth.github.clientSecret").getString(),
            requestMethod = HttpMethod.Post,
            defaultScopes = emptyList()
        )
    }
}

/*@KtorExperimentalAPI
class GitlabLoginProvider : LoginProvider() {
    override val providerName: String = "gitlab"

    override fun getLoginProviderSettings(config: ApplicationConfig): OAuthServerSettings.OAuth2ServerSettings {
        return OAuthServerSettings.OAuth2ServerSettings(
            name = providerName,
            authorizeUrl = "https://github.com/login/oauth/authorize",
            accessTokenUrl = "https://github.com/login/oauth/access_token",
            clientId = config.property("ktor.auth.oauth.github.clientId").getString(),
            clientSecret = config.property("ktor.auth.oauth.github.clientSecret").getString(),
            requestMethod = HttpMethod.Post,
            defaultScopes = emptyList()
        )
    }
}*/

/*@KtorExperimentalAPI
val githubOauthLoginProvider: (ApplicationConfig) -> OAuthServerSettings.OAuth2ServerSettings = { config: ApplicationConfig ->
    OAuthServerSettings.OAuth2ServerSettings(
        name = GithubLoginProvider().providerName,
        authorizeUrl = "https://github.com/login/oauth/authorize",
        accessTokenUrl = "https://github.com/login/oauth/access_token",
        clientId = config.property("ktor.auth.oauth.github.clientId").getString(),
        clientSecret = config.property("ktor.auth.oauth.github.clientSecret").getString(),
        requestMethod = HttpMethod.Post,
        defaultScopes = listOf()
    )
}*/
