package jp.kaleidot725.emomemo.domain.usecase.select

class UpdateSelectedMessageUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository
) {
    suspend fun execute(value: String) {
        val status = statusRepository.get() ?: return
        val message = messageRepository.getByMessageId(status.messageId) ?: return
        val new = message.copy(value = value)
        messageRepository.update(new)
    }
}
