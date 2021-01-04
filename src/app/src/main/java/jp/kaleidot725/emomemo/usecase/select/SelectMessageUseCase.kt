package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class SelectMessageUseCase(private val statusRepository: StatusRepository) {
    suspend fun execute(messageId: Int) {
        val status = statusRepository.get() ?: return
        statusRepository.update(status.notebookId, status.memoId, messageId)
    }
}
