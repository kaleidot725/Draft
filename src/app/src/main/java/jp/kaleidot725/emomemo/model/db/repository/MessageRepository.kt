package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MessageDao
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

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

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun getAll(): List<MessageEntity> {
        return dao.getAll()
    }

    suspend fun getPage(id: Int, no: Int, limit: Int): List<MessageEntity> {
        return dao.getPage(id, (no - 1) * limit, limit)
    }

    suspend fun getMessagesByMemoId(id: Int): List<MessageEntity> {
        return dao.getMessagesByMemoId(id)
    }
}
