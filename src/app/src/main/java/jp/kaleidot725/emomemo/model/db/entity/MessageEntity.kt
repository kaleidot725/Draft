package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import java.util.Date

@Entity(
    tableName = "message",
    primaryKeys = ["memoId", "time"],
    foreignKeys = [ForeignKey(
        entity = MemoEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("memoId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MessageEntity(
    val memoId: Int,
    val time: Long,
    val value: String
) {
    companion object {
        fun create(memoId: Int, value: String): MessageEntity {
            return MessageEntity(memoId, Date().time, value)
        }
    }
}
