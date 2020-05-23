package jp.kaleidot725.emomemo.model.ddd.domainService

import jp.kaleidot725.emomemo.extension.toMessage
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.ddd.domain.Message

class MessageService(private val repository: MessageRepository) {
    suspend fun create(id: Int, message: String) {
        repository.insert(MessageEntity(id, System.currentTimeMillis(), message))
    }

    suspend fun getAll(): List<Message> {
        return repository.getAll().map { it.toMessage() }
    }

    suspend fun getMessage(id: Int): List<Message> {
        return repository.getMessage(id).map { it.toMessage() }
    }
}
