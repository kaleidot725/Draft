package jp.kaleidot725.draft.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notebook")
data class NotebookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String = ""
) : Serializable {
    companion object {
        fun create(title: String): NotebookEntity {
            return NotebookEntity(title = title)
        }
    }
}
