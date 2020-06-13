package online.senpai.webo.repository

import online.senpai.webo.domain.EvolveCharacterLine
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase

private const val DATABASE_NAME = "evolve_lines"

class EvolveRepositoryImpl : EvolveRepository, KoinComponent {
    private val client: CoroutineClient by inject()
    private val database: CoroutineDatabase = client.getDatabase(DATABASE_NAME)

    override suspend fun create(entity: EvolveCharacterLine): EvolveCharacterLine {
        TODO("not implemented")
    }

    override suspend fun update(entity: EvolveCharacterLine): EvolveCharacterLine {
        TODO("not implemented")
    }

    override suspend fun delete(entity: EvolveCharacterLine): EvolveCharacterLine {
        TODO("not implemented")
    }

    override suspend fun listAll(): Iterable<EvolveCharacterLine> {
        return database.listCollectionNames().flatMap { name: String ->
            listAll(name)
        }
    }

    override suspend fun listAll(name: String): Iterable<EvolveCharacterLine> {
        return database.getCollection<EvolveCharacterLine>(name).find().toList().sortedBy { it.lineName }
    }

    override suspend fun rowsNumber(name: String): Long {
        return database.getCollection<EvolveCharacterLine>(name).countDocuments()
    }
}
