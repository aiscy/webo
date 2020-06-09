package online.senpai.webo.module

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import online.senpai.webo.service.EvolveService
import online.senpai.webo.service.EvolveServiceImpl
import online.senpai.webo.service.GithubApiV3
import online.senpai.webo.service.GithubApiV3Impl
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val serviceModule: Module = module(createdAtStart = true) {
    single<HttpClient> {
        HttpClient(Apache) {
            install(JsonFeature) { serializer = JacksonSerializer() }
        }
    }
    singleBy<GithubApiV3, GithubApiV3Impl>()
    singleBy<EvolveService, EvolveServiceImpl>()
}
