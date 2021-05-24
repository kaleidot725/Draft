package jp.kaleidot725.emomemo.domain.usecase.select

class GetSelectedMessageUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository
) {
    suspend fun execute(): jp.kaleidot725.emomemo.data.entity.MessageEntity? {
        val status = statusRepository.get() ?: return null
        return messageRepository.getByMessageId(status.messageId)
    }
}
