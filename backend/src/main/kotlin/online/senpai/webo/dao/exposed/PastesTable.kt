package online.senpai.webo.dao.exposed

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.`java-time`.date
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDate
import java.time.LocalDateTime

object PastesTable : LongIdTable(name = "paste", columnName = "id") {
    val code: Column<String> = text("code")
    val createdAt: Column<LocalDateTime> = datetime("created_at")
    val title: Column<String> = varchar("title", 255).default("Untitled")
    val deleteAfterNumberOfViews: Column<Int?> = integer("delete_after_number_of_views").nullable()
    val expiredAt: Column<LocalDate?> = date("expired_at").nullable()
    val isPrivate: Column<Boolean> = bool("is_private")
    val link: Column<String> = varchar("link", 255).uniqueIndex()
    val totalViews: Column<Int> = integer("total_views").default(0)
    val userId: Column<EntityID<Long>> = reference(
        name = "user_id",
        foreign = UsersTable,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.CASCADE
    )
    val codeLanguageId: Column<EntityID<Long>> = reference(
        name = "code_language_id",
        foreign = CodeLanguagesTable,
        onUpdate = ReferenceOption.CASCADE
    )
}
