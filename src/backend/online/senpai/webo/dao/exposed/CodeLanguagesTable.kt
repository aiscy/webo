package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column


object CodeLanguagesTable : LongIdTable(name = "code_language", columnName = "id") {
    val name: Column<String> = varchar("name", 255)
}
