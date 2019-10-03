package online.senpai.webo.dao.jasync

interface GenericDao<T> {
    suspend fun findById(id: Long): T?
    suspend fun findAll(): List<T?>
    suspend fun countAll(): Int
    suspend fun delete(id: Long)
    suspend fun create(element: T)
    suspend fun modify(id: Long, modification: T)
}
