package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.data.repository.MessageRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class GetSelectedMessageUseCase(
    private val statusRepository: StatusRepository,
    private val messageRepository: MessageRepository
) {
    suspend fun execute(): MessageEntity? {
        val status = statusRepository.get() ?: return null
        return messageRepository.getByMessageId(status.messageId)
    }
}
