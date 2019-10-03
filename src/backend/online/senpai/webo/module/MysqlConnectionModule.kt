package online.senpai.webo.module

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

val mysqlConnectionModule: Module = module {
    single<HikariDataSource> {
        HikariDataSource(HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/ktor"
            username = "root"
            password = "pw"
            maximumPoolSize = 10
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        })
    }
}
