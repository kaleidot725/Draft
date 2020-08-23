package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MessageDao
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

class MessageRepository(private val dao: MessageDao) {
    fun insert(message: MessageEntity) {
        dao.insert(message)
    }

    fun update(message: MessageEntity) {
        dao.update(message)
    }

    fun delete(message: MessageEntity) {
        dao.delete(message)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    fun getAll(): List<MessageEntity> {
        return dao.getAll()
    }

    fun getPage(id: Int, no: Int, limit: Int): List<MessageEntity> {
        return dao.getPage(id, no, limit)
    }

    fun getMessagesByMemoId(id: Int): List<MessageEntity> {
        return dao.getMessagesByMemoId(id)
    }
}
