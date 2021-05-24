package jp.kaleidot725.emomemo.usecase.update

class UpdateMessageUseCase(private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository) {
    suspend fun execute(messageEntity: jp.kaleidot725.emomemo.data.entity.MessageEntity, value: String) {
        val new = jp.kaleidot725.emomemo.data.entity.MessageEntity(messageEntity.id, messageEntity.memoId, messageEntity.time, value)
        messageRepository.update(new)
    }
}
