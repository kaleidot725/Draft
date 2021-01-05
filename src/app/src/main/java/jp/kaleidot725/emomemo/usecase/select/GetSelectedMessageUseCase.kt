package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class GetSelectedMessageUseCase(
    private val statusRepository: StatusRepository,
    private val messageRepository: MessageRepository
) {
    suspend fun execute(): MessageEntity? {
        val status = statusRepository.get() ?: return null
        return messageRepository.getByMessageId(status.messageId)
    }
}
