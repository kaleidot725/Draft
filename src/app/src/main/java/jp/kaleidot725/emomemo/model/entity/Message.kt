package jp.kaleidot725.emomemo.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "message",
    foreignKeys = [ForeignKey(entity = Memo::class, parentColumns = arrayOf("id"), childColumns = arrayOf("memoId"))]
)
data class Message(
    @PrimaryKey val memoId: String,
    val time: Long,
    val value: String
)
