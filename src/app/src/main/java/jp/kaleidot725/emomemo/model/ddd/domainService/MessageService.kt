package jp.kaleidot725.emomemo.model.ddd.domainService

import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class MessasgeService(private val messageRepository: MessageRepository) {
    suspend fun create(id: Int, message: String) {
        messageRepository.insert(MessageEntity(id, System.currentTimeMillis(), message))
    }
}
