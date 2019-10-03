package online.senpai.webo.module

import com.github.jasync.sql.db.SuspendingConnection
import com.github.jasync.sql.db.asSuspending
import com.github.jasync.sql.db.mysql.MySQLConnectionBuilder
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import online.senpai.webo.service.GithubApiV3
import online.senpai.webo.service.GithubApiV3Impl
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy
import java.util.concurrent.TimeUnit

val serviceModule: Module = module(createdAtStart = true) {
    single<HttpClient> {
        HttpClient(Apache) {
            install(JsonFeature) { serializer = JacksonSerializer() }
        }
    }
    singleBy<GithubApiV3, GithubApiV3Impl>()
    single<SuspendingConnection> {
        MySQLConnectionBuilder.createConnectionPool {
            username = "test"
            host = "localhost"
            port = 3306
            password = "123456"
            database = "test"
            maxActiveConnections = 100
            maxIdleTime = TimeUnit.MINUTES.toMillis(15)
            maxPendingQueries = 10_000
            connectionValidationInterval = TimeUnit.SECONDS.toMillis(30)
        }.asSuspending
    }
}
