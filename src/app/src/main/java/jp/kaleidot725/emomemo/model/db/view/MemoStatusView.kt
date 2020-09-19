package jp.kaleidot725.emomemo.model.db.view

import androidx.room.DatabaseView
import androidx.room.PrimaryKey
import java.io.Serializable

@DatabaseView(
    viewName = "memo_status",
    value = """
        SELECT memo.id, memo.notebookId, memo.title, lm.count as count,  lm.time as lastTime, lm.value as lastMessage
        FROM memo LEFT JOIN (
            SELECT message.memoId, COUNT(*) as count, message.time, message.value
            FROM message 
            GROUP BY message.memoId 
            HAVING MAX(message.time)
        ) as lm ON memo.id == lm.memoId
    """
)
data class MemoStatusView(
    @PrimaryKey val id: Int,
    val notebookId: Int,
    val title: String,
    val count: Long?,
    val lastTime: Long?,
    val lastMessage: String?
) : Serializable
