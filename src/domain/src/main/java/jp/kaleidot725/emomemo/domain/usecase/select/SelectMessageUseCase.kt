package jp.kaleidot725.emomemo.domain.usecase.select

class SelectMessageUseCase(private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository) {
    suspend fun execute(messageId: Int) {
        val status = statusRepository.get() ?: return
        statusRepository.update(status.notebookId, status.memoId, messageId)
    }
}
