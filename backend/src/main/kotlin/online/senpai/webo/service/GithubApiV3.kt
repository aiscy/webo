package online.senpai.webo.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import org.koin.core.KoinComponent
import org.koin.core.inject


interface GithubApiV3 {
    suspend fun getUserData(oauthToken: String): GithubApiV3User
}

class GithubApiV3Impl : GithubApiV3, KoinComponent {
    private val client: HttpClient by inject()

    override suspend fun getUserData(oauthToken: String): GithubApiV3User {
        return client.get("https://api.github.com/user") {
            header("Accept", "application/vnd.github.v3+json")
            header("Authorization", "token $oauthToken")
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubApiV3User(
    @JsonProperty("avatar_url") val avatarUrl: String,
    val bio: String,
    val blog: String,
    val company: String,
    @JsonProperty("created_at") val createdAt: String,
    val email: String?,
    @JsonProperty("events_url") val eventsUrl: String,
    val followers: Int,
    @JsonProperty("followers_url") val followersUrl: String,
    val following: Int,
    @JsonProperty("following_url") val followingUrl: String,
    @JsonProperty("gists_url") val gistsUrl: String,
    @JsonProperty("gravatar_id") val gravatarId: String,
    val hireable: Boolean,
    @JsonProperty("html_url") val htmlUrl: String,
    val id: Long,
    val location: String,
    val login: String,
    val name: String,
    @JsonProperty("node_id") val nodeId: String,
    @JsonProperty("organizations_url") val organizationsUrl: String,
    @JsonProperty("public_gists") val publicGists: Int,
    @JsonProperty("public_repos") val publicRepos: Int,
    @JsonProperty("received_events_url") val receivedEventsUrl: String,
    @JsonProperty("repos_url") val reposUrl: String,
    @JsonProperty("site_admin") val siteAdmin: Boolean,
    @JsonProperty("starred_url") val starredUrl: String,
    @JsonProperty("subscriptions_url") val subscriptionsUrl: String,
    val type: String,
    @JsonProperty("updated_at") val updatedAt: String,
    val url: String
)
