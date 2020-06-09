package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption

object GithubUserIdsTable : IdTable<Long>(name = "github_user_id") {
    override val id: Column<EntityID<Long>> = long("id").primaryKey().entityId()
    val userId: Column<EntityID<Long>> = reference("user_id", UsersTable, onUpdate = ReferenceOption.CASCADE)
}
