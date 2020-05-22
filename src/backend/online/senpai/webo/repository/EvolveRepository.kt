package online.senpai.webo.repository

import online.senpai.webo.domain.EvolveCharacterLine

interface EvolveRepository {
    suspend fun listAll(name: String): Iterable<EvolveCharacterLine>
}
