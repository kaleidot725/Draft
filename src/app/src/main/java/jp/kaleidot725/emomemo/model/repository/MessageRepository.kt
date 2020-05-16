package jp.kaleidot725.emomemo.model.repository

import jp.kaleidot725.emomemo.model.dao.MessageDao
import jp.kaleidot725.emomemo.model.entity.Message

val DUMMY_MESSAGE_LIST = listOf(
    Message("0", 0, "Good Message"),
    Message("1", 1, "Good Message"),
    Message("2", 2, "Good Message"),
    Message("3", 3, "Good Message"),
    Message("4", 4, "Good Message"),
    Message("5", 5, "Good Message"),
    Message("6", 6, "Good Message"),
    Message("7", 7, "Good Message"),
    Message("8", 8, "Good Message"),
    Message("9", 9, "Good Message")
)

class MessageRepository(private val dao: MessageDao) {
    suspend fun insert(message: Message) {
        dao.insert(message)
    }

    suspend fun update(message: Message) {
        dao.update(message)
    }

    suspend fun delete(message: Message) {
        dao.delete(message)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun getAll(): List<Message> {
        return dao.getAll()
    }

    suspend fun getUser(id: Int): Message {
        return dao.getUser(id)
    }
}
