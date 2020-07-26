package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notebook")
data class NotebookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = ""
) {
    companion object {
        fun create(title: String): NotebookEntity {
            return NotebookEntity(title = title)
        }
    }
}
