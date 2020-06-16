package online.senpai.webo.service

import online.senpai.webo.model.EvolveModel

interface EvolveService {
    suspend fun characterLines(characterName: String): Iterable<EvolveModel>
    suspend fun specificCharacterLines(
        characterName: String,
        startRow: Int,
        count: Int,
        sortBy: String,
        descending: Boolean
    ): Iterable<EvolveModel>
    suspend fun characterRowsNumber(characterName: String): Long
}

