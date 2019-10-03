package online.senpai.webo.dao

import io.ktor.util.KtorExperimentalAPI
import online.senpai.webo.LoginProvider
import online.senpai.webo.dao.exposed.GithubUserId
import online.senpai.webo.dao.exposed.Paste
import online.senpai.webo.dao.exposed.User
import java.io.Closeable

interface DaoFacade : Closeable {
    fun init()
    suspend fun createNewPaste(): Paste
    suspend fun createNewUser(name: String, avatar: String?): User
    /*suspend fun getUserById(id: Long): User?*/
    @KtorExperimentalAPI
    suspend fun getUserFromLoginProvider(loginProvider: LoginProvider, idAtProvider: Long): User?
    /*suspend fun getGithubUserById(id: Long): GithubUserId*/
}
