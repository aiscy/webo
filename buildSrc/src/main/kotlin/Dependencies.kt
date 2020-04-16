import org.gradle.internal.impldep.junit.runner.Version.id

const val kotlinVersion = "1.3.72"
const val ktor = "1.3.2"
const val koin = "2.1.5"

object Plugins {
    object Versions {
        const val nodeGradle = "2.2.3"
        const val benManesVersions = "0.28.0"

    }

    const val nodeGradle = "com.github.node-gradle.node"
    const val benManesVersions = "com.github.ben-manes.versions"
}

object Libraries {
    private object Versions {
        const val mpierceKtorCsrf = "0.4.1"
        const val kotlinLogging = "1.7.9"
        const val logback = "1.2.1"
        const val hikariCP = "3.4.2"
        const val postgres = "42.2.12"
        const val exposed = "0.23.1"
        const val ktorm = "2.7.2"
    }

    const val ktorServerCore = "io.ktor:ktor-server-core:$ktor"
    const val ktorServerNetty = "io.ktor:ktor-server-netty:$ktor"
    const val ktorHtmlBuilder = "io.ktor:ktor-html-builder:$ktor"
    const val ktorFreemarker = "io.ktor:ktor-freemarker:$ktor"
    const val ktorServerHostCommon = "io.ktor:ktor-server-host-common:$ktor"
    const val ktorLocations = "io.ktor:ktor-locations:$ktor"
    const val ktorMetrics = "io.ktor:ktor-metrics:$ktor"
    const val ktorServerSessions = "io.ktor:ktor-server-sessions:$ktor"
    const val ktorWebsockets = "io.ktor:ktor-websockets:$ktor"
    const val ktorAuth = "io.ktor:ktor-auth:$ktor"
    const val ktorAuthJwt = "io.ktor:ktor-auth-jwt:$ktor"
    const val ktorJackson = "io.ktor:ktor-jackson:$ktor"
    const val ktorClientApache = "io.ktor:ktor-client-apache:$ktor"
    const val ktorClientJson = "io.ktor:ktor-client-json:$ktor"
    const val ktorClientJackson = "io.ktor:ktor-client-jackson:$ktor"
    const val mpierceKtorCsrf = "org.mpierce.ktor.csrf:ktor-csrf:${Versions.mpierceKtorCsrf}"
    const val kotlinLogging = "io.github.microutils:kotlin-logging:${Versions.kotlinLogging}"
    const val logbackClassic = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val hikariCP = "com.zaxxer:HikariCP:${Versions.hikariCP}"
    const val postgres = "org.postgresql:postgresql:${Versions.postgres}"
    const val exposedCore = "org.jetbrains.exposed:exposed-core:${Versions.exposed}"
    const val exposedDao = "org.jetbrains.exposed:exposed-dao:${Versions.exposed}"
    const val exposedJdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}"
    const val ktormCore = "me.liuwj.ktorm:ktorm-core:${Versions.ktorm}"
    const val ktormPostgre = "me.liuwj.ktorm:ktorm-support-postgresql:${Versions.ktorm}"
    const val koinCore = "org.koin:koin-core:$koin"
    const val koinKtor = "org.koin:koin-ktor:$koin"
    const val koinSlf4j = "org.koin:koin-logger-slf4j:$koin"
}

object TestLibraries {
    private object Versions {
        const val testcontainers = "1.14.0"
    }

    const val ktorServerTests = "io.ktor:ktor-server-tests:$ktor"
    const val koinTest = "org.koin:koin-test:$koin"
    const val testContainersJupiter = "org.testcontainers:junit-jupiter:${Versions.testcontainers}"
    const val testContainersNginx = "org.testcontainers:nginx:${Versions.testcontainers}"
}
