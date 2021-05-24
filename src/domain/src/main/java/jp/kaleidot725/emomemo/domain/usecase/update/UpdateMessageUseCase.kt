package jp.kaleidot725.emomemo.domain.usecase.update

import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.data.repository.MessageRepository

class UpdateMessageUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(messageEntity: jp.kaleidot725.emomemo.data.entity.MessageEntity, value: String) {
        val new = MessageEntity(messageEntity.id, messageEntity.memoId, messageEntity.time, value)
        messageRepository.update(new)
    }
}
