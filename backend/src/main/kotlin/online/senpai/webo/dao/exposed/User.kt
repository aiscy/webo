package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(UsersTable)

    var name: String by UsersTable.name
    var avatar: String by UsersTable.avatar
}
