package jp.kaleidot725.emomemo.usecase.delete

import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class DeleteMessagesUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(messages: List<MessageEntity>) {
        messageRepository.delete(messages.map { it.id })
    }
}
