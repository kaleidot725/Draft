package jp.kaleidot725.emomemo.domain.usecase.create

import jp.kaleidot725.emomemo.data.repository.MessageRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class CreateMessageUseCase(
    private val statusRepository: StatusRepository,
    private val messageRepository: MessageRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        messageRepository.insert(jp.kaleidot725.emomemo.data.entity.MessageEntity.create(status.memoId, title))
    }
}
