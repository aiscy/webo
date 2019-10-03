package online.senpai.webo.dao.jasync

import com.github.jasync.sql.db.ResultSet
import com.github.jasync.sql.db.RowData
import online.senpai.webo.model.User


class UserDao : AbstractJasyncDao<User>() {
    override suspend fun findById(id: Long): User? {
        val result: RowData? = connection.sendPreparedStatement( //language=MySQL
            query = "SELECT id, avatar, name FROM user WHERE id = ? LIMIT 1",
            values = listOf(id)
        ).rows.firstOrNull()
        return if (result != null) {
            User(
                id = result.getAs("id"),
                avatar = result.getAs("avatar"),
                name = result.getAs("name")
            )
        } else {
            null
        }
    }

    override suspend  fun findAll(): List<User?> {
        val resultSet: ResultSet = connection.sendPreparedStatement( //language=MySQL
            query = "SELECT id, avatar, name FROM user"
        ).rows
        return resultSet.map {
            User(
                id = it.getAs("id"),
                name = it.getAs("name"),
                avatar = it.getAs("avatar")
            )
        }
    }

    override suspend  fun countAll(): Int {
        TODO("not implemented")
    }

    override suspend  fun delete(id: Long) {
        TODO("not implemented")
    }

    override suspend  fun create(element: User) {
        TODO("not implemented")
    }

    override suspend  fun modify(id: Long, modification: User) {
        TODO("not implemented")
    }
}
