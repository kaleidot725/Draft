package jp.kaleidot725.emomemo.usecase.update

import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class UpdateMessageUseCase(private val messageRepository: MessageRepository) {
    suspend fun execute(messageEntity: MessageEntity, value: String) {
        val new = MessageEntity(messageEntity.id, messageEntity.memoId, messageEntity.time, value)
        messageRepository.update(new)
    }
}
