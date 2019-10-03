package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.Column


object UsersTable : LongIdTable(name = "user", columnName = "id") {
    val avatar: Column<String> = varchar("avatar", 255).default("default_avatar.png")
    val name: Column<String> = varchar("name", 255)
}
