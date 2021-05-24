package jp.kaleidot725.emomemo.domain.usecase.delete

import jp.kaleidot725.emomemo.data.repository.MessageRepository

class DeleteMessagesUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(messages: List<jp.kaleidot725.emomemo.data.entity.MessageEntity>) {
        messageRepository.delete(messages.map { it.id })
    }
}
