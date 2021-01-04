package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class UpdateSelectedMessageUseCase(
    private val statusRepository: StatusRepository,
    private val messageRepository: MessageRepository
) {
    suspend fun execute(value: String) {
        val status = statusRepository.get() ?: return
        val message = messageRepository.getByMessageId(status.messageId) ?: return
        val new = message.copy(value = value)
        messageRepository.update(new)
    }
}
