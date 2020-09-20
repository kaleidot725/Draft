package jp.kaleidot725.emomemo.model.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "status")
data class StatusEntity(
    @PrimaryKey
    val id: Int = 0,
    val notebookId: Int = UNSELECTED_NOTEBOOK,
    val memoId: Int = UNSELECTED_MEMO
) : Serializable {
    companion object {
        const val UNSELECTED_NOTEBOOK = -1
        const val UNSELECTED_MEMO = -1
    }
}
