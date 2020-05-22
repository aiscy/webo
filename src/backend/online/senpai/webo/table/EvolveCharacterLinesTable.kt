package online.senpai.webo.table

import online.senpai.webo.dao.exposed.EvolveCharacter
import online.senpai.webo.table.common.PostgresEnum
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object EvolveCharacterLinesTable : Table(name = "evolve_character_lines") {
    val id: Column<Int> = integer("id").autoIncrement()
    override val primaryKey = PrimaryKey(id)
    val lineName: Column<String> = varchar(name = "line_name", length = 80)
    val lineText: Column<String> = text("line_text")
    val lineOwner: Column<EvolveCharacter> = customEnumeration(
        name = "line_owner",
        sql = "evolve_character",
        fromDb = { value: Any -> EvolveCharacter.valueOf(value as String) },
        toDb = { PostgresEnum("evolve_character", it) }
    )
    val filePath: Column<String> = varchar(name = "file_path", length = 255)
}
