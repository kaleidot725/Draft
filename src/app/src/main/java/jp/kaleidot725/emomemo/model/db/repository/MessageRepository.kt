package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MessageDao
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

val DUMMY_MESSAGE_LIST = listOf(
    MessageEntity(0, 0, "Good Message"),
    MessageEntity(1, 1, "Good Message"),
    MessageEntity(2, 2, "Good Message"),
    MessageEntity(3, 3, "Good Message"),
    MessageEntity(4, 4, "Good Message"),
    MessageEntity(5, 5, "Good Message"),
    MessageEntity(6, 6, "Good Message"),
    MessageEntity(7, 7, "Good Message"),
    MessageEntity(8, 8, "Good Message"),
    MessageEntity(9, 9, "Good Message")
)

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

    fun getMessage(id: Int): List<MessageEntity> {
        return dao.getMessage(id)
    }
}
