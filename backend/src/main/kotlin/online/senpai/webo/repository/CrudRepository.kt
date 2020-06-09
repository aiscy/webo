package online.senpai.webo.repository

interface CrudRepository<T> {
    suspend fun create(entity: T): T
    suspend fun update(entity: T): T
    suspend fun delete(entity: T): T
    suspend fun listAll(): Iterable<T>
}
