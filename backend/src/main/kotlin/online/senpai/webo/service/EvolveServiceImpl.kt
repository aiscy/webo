package online.senpai.webo.service

import online.senpai.webo.domain.EvolveCharacterLine
import online.senpai.webo.model.evolve.LinesModel
import online.senpai.webo.model.evolve.RowsNumberModel
import online.senpai.webo.repository.EvolveRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class EvolveServiceImpl : EvolveService, KoinComponent {
    private val repository: EvolveRepository by inject()

    override suspend fun characterLines(characterName: String): LinesModel {
        return LinesModel(
            lines = repository
                .listAll(characterName)
                .map { characterLine: EvolveCharacterLine ->
                    LinesModel.LineModel(
                        name = characterLine.lineName,
                        text = characterLine.lineText,
                        files = characterLine.filePath
                    )
                }
        )
    }

    override suspend fun characterLines(
        characterName: String,
        startRow: Int,
        count: Int,
        sortBy: String,
        descending: Boolean
    ): LinesModel {
        return LinesModel(
            lines = repository
                .listAll(characterName, startRow, count, sortBy, descending)
                .map { line: EvolveCharacterLine ->
                    LinesModel.LineModel(
                        name = line.lineName,
                        text = line.lineText,
                        files = line.filePath
                    )
                }
        )
    }

    override suspend fun characterRowsNumber(name: String): RowsNumberModel {
        return RowsNumberModel(rowsNumber = repository.rowsNumber(name))
    }
}
