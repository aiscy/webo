package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.*

object CodeLanguagesTable : LongIdTable(name = "code_language", columnName = "id") {
    val name: Column<String> = varchar("name", 255)
}
