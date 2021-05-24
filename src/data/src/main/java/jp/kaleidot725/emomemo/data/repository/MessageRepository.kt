package jp.kaleidot725.emomemo.data.repository

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.data.dao.MessageDao
import jp.kaleidot725.emomemo.data.entity.MessageEntity

class MessageRepository(private val dao: MessageDao) {
    suspend fun insert(message: MessageEntity) {
        dao.insert(message)
    }

    suspend fun update(message: MessageEntity) {
        dao.update(message)
    }

    suspend fun delete(message: MessageEntity) {
        dao.delete(message)
    }

    suspend fun delete(messageIds: List<Int>) {
        dao.delete(messageIds)
    }

    suspend fun getPageAsc(memoId: Int, no: Int, limit: Int): List<MessageEntity> {
        return dao.getPageAsc(memoId, (no - 1) * limit, limit)
    }

    suspend fun getPageDesc(memoId: Int, no: Int, limit: Int): List<MessageEntity> {
        return dao.getPageDesc(memoId, (no - 1) * limit, limit)
    }

    suspend fun getMessageCount(memoId: Int): Int {
        return dao.getMessageCount(memoId)
    }

    suspend fun getByMessageId(messageId: Int): MessageEntity? {
        return dao.getByMessageId(messageId)
    }

    fun getMessageCountLiveData(memoId: Int): LiveData<Int> {
        return dao.getMessageCountLiveData(memoId)
    }
}
