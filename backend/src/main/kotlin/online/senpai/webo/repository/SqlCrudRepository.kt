package online.senpai.webo.repository

interface SqlCrudRepository<T, K : Number> : CrudRepository<T> {
    fun createTable()
    suspend fun findById(id: K): T?
}
