package jp.kaleidot725.emomemo.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "message",
    primaryKeys = ["memoId", "time"],
    foreignKeys = [ForeignKey(entity = Memo::class, parentColumns = arrayOf("id"), childColumns = arrayOf("memoId"))]
)
data class Message(
    val memoId: Int,
    val time: Long,
    val value: String
)
