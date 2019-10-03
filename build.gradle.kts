import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val koinVersion: String by project
val exposedVersion: String by project
val kotlinLoggingVersion: String by project
val slf4jApiVersion: String by project
val hikaricpVersion: String by project
val testcontainersJunitVersion: String by project
val ktorCsrfVersion: String by project
val jasyncVersion: String by project
val mysqlVersion: String by project
val postgresVersion: String by project

plugins {
    application
    kotlin("jvm") version "1.3.50"
    id("com.moowork.node") version "1.3.1"
    id("com.github.ben-manes.versions") version "0.25.0"
}

group = "online.senpai.webo"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
}

dependencies {
    /*implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")*/

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-freemarker:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-client-json:$ktorVersion")
    implementation("io.ktor:ktor-client-jackson:$ktorVersion")

    implementation("org.mpierce.ktor.csrf:ktor-csrf:$ktorCsrfVersion")

    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("com.zaxxer:HikariCP:$hikaricpVersion")
    implementation("mysql:mysql-connector-java:$mysqlVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("org.jetbrains.exposed:exposed:$exposedVersion")
    implementation("com.github.jasync-sql:jasync-mysql:$jasyncVersion")

    implementation("org.koin:koin-core:$koinVersion")
    implementation("org.koin:koin-ktor:$koinVersion")
    implementation("org.koin:koin-logger-slf4j:$koinVersion")

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation("org.koin:koin-test:$koinVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersJunitVersion")
    testImplementation("org.testcontainers:nginx:$testcontainersJunitVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

node {
    download = true
    version = "10.16.0"
    npmVersion = "2.1.5"
    yarnVersion = "1.16.0"
    workDir = file("${project.buildDir}/src/frontend")
    nodeModulesDir = file("${project.projectDir}")
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "11"
    }
}

kotlin.sourceSets["main"].kotlin.srcDirs("src/backend")
kotlin.sourceSets["test"].kotlin.srcDirs("test/backend")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")
