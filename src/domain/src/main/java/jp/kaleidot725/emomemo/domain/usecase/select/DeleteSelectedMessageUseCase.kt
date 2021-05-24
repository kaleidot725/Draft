package jp.kaleidot725.emomemo.domain.usecase.select

class DeleteSelectedMessageUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository
) {
    suspend fun execute() {
        val status = statusRepository.get() ?: return
        messageRepository.delete(listOf(status.messageId))
    }
}
