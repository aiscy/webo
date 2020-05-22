package online.senpai.webo.module

import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import org.koin.core.module.Module
import org.koin.dsl.module
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

private const val JDBC_MAX_POOL_SIZE = 20

val dbConnectionModule: Module = module(createdAtStart = true) {
    /*single<HikariDataSource> {
        HikariDataSource(HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/"
            username = "postgres"
            password = "dev"
            maximumPoolSize = JDBC_MAX_POOL_SIZE
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })
    }*/
    single<CoroutineClient> {
        val settings: MongoClientSettings = MongoClientSettings
            .builder()
            .apply {
                credential(MongoCredential.createScramSha1Credential("mongo", "admin", "mongo".toCharArray()))
            }
            .build()
        KMongo.createClient(settings).coroutine
    }
}
