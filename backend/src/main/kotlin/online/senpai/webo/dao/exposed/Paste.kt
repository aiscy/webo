package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.time.LocalDate
import java.time.LocalDateTime

class Paste(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Paste>(PastesTable)

    var code: String by PastesTable.code
    var createdAt: LocalDateTime by PastesTable.createdAt
    var title: String by PastesTable.title
    var deleteAfterNumberOfViews: Int? by PastesTable.deleteAfterNumberOfViews
    var expiredAt: LocalDate? by PastesTable.expiredAt
    var isPrivate: Boolean by PastesTable.isPrivate
    var link: String by PastesTable.link
    var totalViews: Int by PastesTable.totalViews
    var userId: User by User referencedOn PastesTable.userId
    var codeLanguageId: CodeLanguage by CodeLanguage referencedOn PastesTable.codeLanguageId
}
