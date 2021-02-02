package jp.kaleidot725.emomemo.usecase.create

import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class CreateMessageUseCase(
    private val statusRepository: StatusRepository,
    private val messageRepository: MessageRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        messageRepository.insert(MessageEntity.create(status.memoId, title))
    }
}
