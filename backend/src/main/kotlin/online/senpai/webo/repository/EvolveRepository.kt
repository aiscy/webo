package online.senpai.webo.repository

import online.senpai.webo.domain.EvolveCharacterLine

private typealias Domain = EvolveCharacterLine

interface EvolveRepository : MongoCrudRepository<Domain> {
    suspend fun listAll(name: String): Iterable<Domain>
    suspend fun rowsNumber(name: String): Long
}
