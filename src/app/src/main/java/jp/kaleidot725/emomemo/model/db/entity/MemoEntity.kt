package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val tag: String,
    val title: String
)
