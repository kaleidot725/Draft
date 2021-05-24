package jp.kaleidot725.emomemo.domain.usecase.delete

class DeleteMessagesUseCase(private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository) {
    suspend fun execute(messages: List<jp.kaleidot725.emomemo.data.entity.MessageEntity>) {
        messageRepository.delete(messages.map { it.id })
    }
}
