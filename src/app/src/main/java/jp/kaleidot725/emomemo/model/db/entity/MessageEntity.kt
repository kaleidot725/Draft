package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "message",
    primaryKeys = ["memoId", "time"],
    foreignKeys = [ForeignKey(
        entity = MemoEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("memoId")
    )]
)
data class MessageEntity(
    val memoId: Int,
    val time: Long,
    val value: String
)
