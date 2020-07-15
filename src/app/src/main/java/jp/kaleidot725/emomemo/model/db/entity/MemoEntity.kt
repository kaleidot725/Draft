package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "memo",
    foreignKeys = [ForeignKey(
        entity = NotebookEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("notebookId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val notebookId: Int,
    val title: String
)
