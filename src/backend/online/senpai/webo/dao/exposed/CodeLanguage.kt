package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass

class CodeLanguage(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<CodeLanguage>(CodeLanguagesTable)

    var name: String by CodeLanguagesTable.name
}
