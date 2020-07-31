package online.senpai.webo.service

import online.senpai.webo.model.evolve.LinesModel
import online.senpai.webo.model.evolve.RowsNumberModel

interface EvolveService {
    suspend fun characterLines(characterName: String): LinesModel
    suspend fun characterLines(
        characterName: String,
        startRow: Int,
        count: Int,
        sortBy: String,
        descending: Boolean
    ): LinesModel
    suspend fun characterRowsNumber(name: String): RowsNumberModel
}
