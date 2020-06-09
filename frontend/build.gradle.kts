import com.moowork.gradle.node.yarn.YarnTask

plugins {
    id(Plugins.nodeGradle) version Plugins.Versions.nodeGradle // TODO
}

node {
    download = true

    version = "12.17.0"
    workDir = file("${project.rootProject.projectDir}/.gradle/nodejs")
    yarnVersion = "1.22.4"
    yarnWorkDir = file("${project.rootProject.projectDir}/.gradle/yarn")
}

tasks.create<YarnTask>("runDev") {
    args = listOf("dev")
}
