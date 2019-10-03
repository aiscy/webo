package online.senpai.webo.module

import online.senpai.webo.dao.DaoCache
import online.senpai.webo.dao.DaoFacade
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val daoModule: Module = module {
    single<DaoFacade> { DaoCache(delegate = get()) }
}
