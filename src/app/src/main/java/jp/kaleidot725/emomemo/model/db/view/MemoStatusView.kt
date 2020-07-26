package jp.kaleidot725.emomemo.model.db.view

import androidx.room.DatabaseView
import androidx.room.PrimaryKey

@DatabaseView(
    viewName = "memo_status",
    value = """
        SELECT memo.id, memo.notebookId, memo.title, count.value as count, lastMessage.time as lastTime, lastMessage.value as lastMessage
        FROM memo
            LEFT OUTER JOIN (SELECT COUNT(*) as value FROM memo, message WHERE memo.id == message.memoId) as count
            LEFT OUTER JOIN (SELECT * FROM message ORDER BY message.time DESC LIMIT 1) as lastMessage
    """
)
data class MemoStatusView(
    @PrimaryKey val id: Int,
    val notebookId: Int,
    val title: String,
    val count: Long?,
    val lastTime: Long?,
    val lastMessage: String?
)
