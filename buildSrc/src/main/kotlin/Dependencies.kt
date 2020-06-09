const val KOTLIN_VERSION = "1.3.72"
const val KTOR = "1.3.2"
const val KOIN = "2.1.5"

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
        const val pgjdbcNg = "0.8.4"
        const val krush = "0.2.0"
        const val mpierceKtorCsrf = "0.4.1"
        const val kotlinLogging = "1.7.9"
        const val logback = "1.2.1"
        const val hikariCP = "3.4.2"
        const val postgres = "42.2.12"
        const val exposed = "0.23.1"
        const val kmongo = "4.0.0"
        const val kotlinxSerialization = "0.20.0"
    }

    const val ktorServerCore = "io.ktor:ktor-server-core:$KTOR"
    const val ktorServerNetty = "io.ktor:ktor-server-netty:$KTOR"
    const val ktorHtmlBuilder = "io.ktor:ktor-html-builder:$KTOR"
    const val ktorFreemarker = "io.ktor:ktor-freemarker:$KTOR"
    const val ktorServerHostCommon = "io.ktor:ktor-server-host-common:$KTOR"
    const val ktorLocations = "io.ktor:ktor-locations:$KTOR"
    const val ktorMetrics = "io.ktor:ktor-metrics:$KTOR"
    const val ktorServerSessions = "io.ktor:ktor-server-sessions:$KTOR"
    const val ktorWebsockets = "io.ktor:ktor-websockets:$KTOR"
    const val ktorAuth = "io.ktor:ktor-auth:$KTOR"
    const val ktorAuthJwt = "io.ktor:ktor-auth-jwt:$KTOR"
    const val ktorJackson = "io.ktor:ktor-jackson:$KTOR"
    const val ktorClientApache = "io.ktor:ktor-client-apache:$KTOR"
    const val ktorClientJson = "io.ktor:ktor-client-json:$KTOR"
    const val ktorClientJackson = "io.ktor:ktor-client-jackson:$KTOR"
    const val ktorSerialization = "io.ktor:ktor-serialization:$KTOR"
    const val mpierceKtorCsrf = "org.mpierce.ktor.csrf:ktor-csrf:${Versions.mpierceKtorCsrf}"
    const val kotlinLogging = "io.github.microutils:kotlin-logging:${Versions.kotlinLogging}"
    const val logbackClassic = "ch.qos.logback:logback-classic:${Versions.logback}"
    const val hikariCP = "com.zaxxer:HikariCP:${Versions.hikariCP}"
    const val postgres = "org.postgresql:postgresql:${Versions.postgres}"
    const val pgjdbcNg = "com.impossibl.pgjdbc-ng:pgjdbc-ng:${Versions.pgjdbcNg}"
    const val krushAnnotationProcessor = "pl.touk.krush:annotation-processor:${Versions.krush}"
    const val krushRuntime = "pl.touk.krush:runtime:${Versions.krush}"
    const val exposedCore = "org.jetbrains.exposed:exposed-core:${Versions.exposed}"
    const val exposedDao = "org.jetbrains.exposed:exposed-dao:${Versions.exposed}"
    const val exposedJdbc = "org.jetbrains.exposed:exposed-jdbc:${Versions.exposed}"
    const val exposedJavaTime = "org.jetbrains.exposed:exposed-java-time:${Versions.exposed}"
    const val koinCore = "org.koin:koin-core:$KOIN"
    const val koinKtor = "org.koin:koin-ktor:$KOIN"
    const val koinSlf4j = "org.koin:koin-logger-slf4j:$KOIN"
    const val kmongoCoroutine = "org.litote.kmongo:kmongo-coroutine:${Versions.kmongo}"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerialization}"
}

object TestLibraries {
    private object Versions {
        const val testcontainers = "1.14.0"
    }

    const val ktorServerTests = "io.ktor:ktor-server-tests:$KTOR"
    const val koinTest = "org.koin:koin-test:$KOIN"
    const val testContainersJupiter = "org.testcontainers:junit-jupiter:${Versions.testcontainers}"
    const val testContainersNginx = "org.testcontainers:nginx:${Versions.testcontainers}"
}
