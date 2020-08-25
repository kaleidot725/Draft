package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status")
data class StatusEntity(
    @PrimaryKey
    val id: Int = 0,
    val notebookId: Int = 0,
    val memoId: Int = 0
)
