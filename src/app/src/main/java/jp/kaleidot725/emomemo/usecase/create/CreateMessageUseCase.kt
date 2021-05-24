package jp.kaleidot725.emomemo.usecase.create

class CreateMessageUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        messageRepository.insert(jp.kaleidot725.emomemo.data.entity.MessageEntity.create(status.memoId, title))
    }
}
