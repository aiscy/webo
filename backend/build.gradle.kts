plugins {
    application
    kotlin("jvm") version KOTLIN_VERSION
    /*kotlin("kapt") version kotlinVersion*/
    kotlin("plugin.serialization") version KOTLIN_VERSION
}

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    maven { url = uri("https://philanthropist.touk.pl/nexus/content/repositories/releases") }
}

dependencies {
    // Ktor
    implementation(Libraries.ktorServerCore)
    implementation(Libraries.ktorServerNetty)
    implementation(Libraries.ktorHtmlBuilder)
    implementation(Libraries.ktorFreemarker)
    implementation(Libraries.ktorServerHostCommon)
    implementation(Libraries.ktorLocations)
    implementation(Libraries.ktorMetrics)
    implementation(Libraries.ktorServerSessions)
    implementation(Libraries.ktorWebsockets)
    implementation(Libraries.ktorAuth)
    implementation(Libraries.ktorAuthJwt)
    implementation(Libraries.ktorJackson)
    implementation(Libraries.ktorSerialization)
    implementation(Libraries.mpierceKtorCsrf)

    // Ktor client
    implementation(Libraries.ktorClientApache)
    implementation(Libraries.ktorClientJson)
    implementation(Libraries.ktorClientJackson)

    // Logging
    implementation(Libraries.kotlinLogging)
    implementation(Libraries.logbackClassic)

    // Database
    implementation(Libraries.hikariCP)
    implementation(Libraries.postgres)
    implementation(Libraries.exposedCore)
    implementation(Libraries.exposedDao)
    implementation(Libraries.exposedJdbc)
    implementation(Libraries.exposedJavaTime)
    /*api(Libraries.krushAnnotationProcessor)
    kapt(Libraries.krushAnnotationProcessor)
    api(Libraries.krushRuntime)*/
    implementation(Libraries.kmongoCoroutine)
    /*implementation(Libraries.kotlinxSerialization)*/

    // Dependency injection
    implementation(Libraries.koinCore)
    implementation(Libraries.koinKtor)
    implementation(Libraries.koinSlf4j)

    testImplementation(TestLibraries.ktorServerTests)
    testImplementation(TestLibraries.koinTest)
    testImplementation(TestLibraries.testContainersJupiter)
    testImplementation(TestLibraries.testContainersNginx)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin")
        resources.srcDirs("src/main/resources")
    }
    test {
        java.srcDirs("src/test/kotlin")
        resources.srcDirs("src/test/resources")
    }
}
