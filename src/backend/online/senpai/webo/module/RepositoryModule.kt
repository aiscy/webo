package online.senpai.webo.module

import online.senpai.webo.repository.EvolveRepository
import online.senpai.webo.repository.EvolveRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val repositoryModule: Module = module(createdAtStart = true) {
    singleBy<EvolveRepository, EvolveRepositoryImpl>()
}
