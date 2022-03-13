package jp.kaleidot725.emomemo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

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
    @ColumnInfo(index = true)
    val notebookId: Int,
    val title: String,
    val content: String
) : Serializable {
    companion object {
        fun create(notebookId: Int, title: String, content: String): MemoEntity {
            return MemoEntity(0, notebookId, title, content)
        }
    }
}
