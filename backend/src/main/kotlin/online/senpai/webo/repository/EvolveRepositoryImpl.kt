package online.senpai.webo.repository

import org.koin.core.KoinComponent
import org.koin.core.inject
import org.litote.kmongo.ascending
import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.descending

private const val DATABASE_NAME = "evolve"

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

    override suspend fun listAll(
        name: String,
        startRow: Int,
        count: Int,
        sortBy: String, // TODO make a support
        descending: Boolean
    ): Iterable<EvolveDomain> {
        return database
            .getCollection<EvolveDomain>(name)
            .find()
            .sort(
                if (descending) {
                    descending(EvolveDomain::lineName)
                } else {
                    ascending(EvolveDomain::lineName)
                }
            )
            .skip(startRow)
            .limit(count)
            .toList()
    }

    override suspend fun listAll(name: String): Iterable<EvolveDomain> {
        return database.getCollection<EvolveDomain>(name).find().sort(ascending(EvolveDomain::lineName)).toList()
    }

    override suspend fun rowsNumber(name: String): Long {
        return database.getCollection<EvolveDomain>(name).countDocuments()
    }
}
