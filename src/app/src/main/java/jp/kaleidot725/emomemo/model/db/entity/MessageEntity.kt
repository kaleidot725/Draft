package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(
    tableName = "message",
    foreignKeys = [ForeignKey(
        entity = MemoEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("memoId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val memoId: Int,
    val time: Long,
    val value: String
) : Serializable {
    companion object {
        fun create(memoId: Int, value: String): MessageEntity {
            return MessageEntity(0, memoId, Date().time, value)
        }
    }
}
