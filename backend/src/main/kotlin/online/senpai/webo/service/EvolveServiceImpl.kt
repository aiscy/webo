package online.senpai.webo.service

import online.senpai.webo.domain.EvolveCharacterLine
import online.senpai.webo.model.EvolveModel
import online.senpai.webo.repository.EvolveRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class EvolveServiceImpl : KoinComponent, EvolveService {
    private val repository: EvolveRepository by inject()

    override suspend fun characterLines(characterName: String): Iterable<EvolveModel> {
        return repository
            .listAll(characterName)
            .mapIndexed { i: Int, characterLine: EvolveCharacterLine ->
                EvolveModel(
                    id = i,
                    lineName = characterLine.lineName,
                    lineText = characterLine.lineText,
                    filePath = characterLine.filePath
                )
            }
    }

    override suspend fun characterRowsNumber(characterName: String): Long {
        return repository.rowsNumber(characterName)
    }
}
