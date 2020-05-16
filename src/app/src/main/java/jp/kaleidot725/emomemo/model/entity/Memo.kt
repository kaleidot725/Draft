package jp.kaleidot725.emomemo.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class Memo(
    @PrimaryKey(autoGenerate = false) val id: String,
    val tag: String,
    val title: String,
    val detail: String
)
