package jp.kaleidot725.emomemo.model.db.repository

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.dao.MessageDao
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

class MessageRepository(private val dao: MessageDao) {
    suspend fun insert(message: MessageEntity) {
        dao.insert(message)
    }

    suspend fun delete(message: MessageEntity) {
        dao.delete(message)
    }

    suspend fun getPage(memoId: Int, no: Int, limit: Int): List<MessageEntity> {
        return dao.getPage(memoId, (no - 1) * limit, limit)
    }

    fun getMessageCount(memoId: Int): LiveData<Int> {
        return dao.getMessageCount(memoId)
    }
}
