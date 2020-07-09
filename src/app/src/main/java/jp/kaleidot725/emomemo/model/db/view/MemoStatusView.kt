package jp.kaleidot725.emomemo.model.db.view

import androidx.room.DatabaseView
import androidx.room.PrimaryKey

@DatabaseView(
    viewName = "memo_status",
    value = """
        SELECT memo.id, memo.tag, memo.title, count.value, lastMessage.time, lastMessage.value
        FROM memo
        INNER JOIN (SELECT COUNT(*) as value FROM memo, message WHERE memo.id == message.memoId) as count
        INNER JOIN (SELECT * FROM message WHERE MAX(message.time) ) as lastMessage
    """
)
data class MemoStatusView(
    @PrimaryKey val id: Int,
    val tag: String,
    val title: String,
    val count: Long,
    val lastTime: Long,
    val lastMessage: String
)
