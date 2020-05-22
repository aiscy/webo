import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version kotlinVersion
    /*kotlin("kapt") version kotlinVersion*/
    kotlin("plugin.serialization") version kotlinVersion
    id(Plugins.nodeGradle) version Plugins.Versions.nodeGradle // TODO
    id(Plugins.benManesVersions) version Plugins.Versions.benManesVersions
}

group = "online.senpai.webo"

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

node {
    download = true
    version = "12.16.2"
    npmVersion = "6.14.4"
    yarnVersion = "1.22.4"
    workDir = file("${project.buildDir}/src/frontend")
    nodeModulesDir = file("${project.projectDir}")
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "11"
    }
}

sourceSets {
    main {
        java.srcDirs("src/backend")
        resources.srcDirs("resources")
    }
    test {
        java.srcDirs("test/backend")
        resources.srcDirs("testresources")
    }
}

tasks.register<JavaExec>("vlcjTestApp") {
    group = "playground"
    main = "online.senpai.owoard.playground.VlcjTestApp"
    classpath = sourceSets.test.get().runtimeClasspath
}
