package online.senpai.webo.repository

import org.koin.core.KoinComponent
import org.koin.core.inject
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase

private const val DATABASE_NAME = "evolve_lines"

class EvolveRepositoryImpl : EvolveRepository, KoinComponent {
    private val client: CoroutineClient by inject()
    private val database: CoroutineDatabase = client.getDatabase(DATABASE_NAME)

    override suspend fun create(entity: EvolveDomain): EvolveDomain {
        TODO("not implemented")
    }

    override suspend fun update(entity: EvolveDomain): EvolveDomain {
        TODO("not implemented")
    }

    override suspend fun delete(entity: EvolveDomain): EvolveDomain {
        TODO("not implemented")
    }

    override suspend fun listAll(): Iterable<EvolveDomain> {
        return database.listCollectionNames().flatMap { name: String ->
            listAll(name)
        }
    }

    override suspend fun listSpecific(
        name: String,
        startRow: Int,
        count: Int,
        sortBy: String,
        descending: Boolean
    ): Iterable<EvolveDomain> {
        return database.getCollection<EvolveDomain>(name).find().skip(startRow).limit(count).toList()
    }

    override suspend fun listAll(name: String): Iterable<EvolveDomain> {
        return database.getCollection<EvolveDomain>(name).find().toList().sortedBy { it.lineName }
    }

    override suspend fun rowsNumber(name: String): Long {
        return database.getCollection<EvolveDomain>(name).countDocuments()
    }
}
