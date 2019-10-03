package online.senpai.webo.dao.exposed

import com.zaxxer.hikari.HikariDataSource
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.Dispatchers
import mu.KLogger
import mu.KotlinLogging
import online.senpai.webo.GithubLoginProvider
import online.senpai.webo.LoginProvider
import online.senpai.webo.dao.DaoFacade
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.inject

private val logger: KLogger = KotlinLogging.logger {}

class Dao : DaoFacade, KoinComponent {
    // TODO Dunno how to organize it in a proper way, so let's keep it as a god object for now :-[
    private val dataSource: HikariDataSource by inject()
    private val db: Database = Database.connect(dataSource)

    override fun init() {
        transaction(db) {
            SchemaUtils.createMissingTablesAndColumns(
                CodeLanguagesTable,
                GithubUserIdsTable,
                PastesTable,
                UsersTable
            )
        }
    }

    override suspend fun createNewPaste(): Paste {
        return newSuspendedTransaction(Dispatchers.IO) {
            Paste.new {
                code
            }
        }
    }

    override suspend fun createNewUser(name: String, avatar: String?): User {
        return newSuspendedTransaction(Dispatchers.IO) {
            User.new {
                this.name = name
                if (avatar != null) this.avatar = avatar
            }
        }
    }

    @KtorExperimentalAPI
    override suspend fun getUserFromLoginProvider(loginProvider: LoginProvider, idAtProvider: Long): User? {
        return when (loginProvider) {
            is GithubLoginProvider -> GithubUserId.findById(idAtProvider)?.userId
            /*is GitlabLoginProvider -> GitlabUserId.findById(idAtProvider)?.userId*/
        }
    }

    override fun close() {
        logger.debug { "Close method was invoked." }
        if (!dataSource.isClosed) {
            logger.info { "Shutdown the datasource pool..." }
            dataSource.close()
        }
    }
}
