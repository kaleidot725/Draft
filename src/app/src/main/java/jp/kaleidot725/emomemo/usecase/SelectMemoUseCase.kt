package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class SelectMemoUseCase(private val statusRepository: StatusRepository) {
    suspend fun execute(memoId: Int) {
        val status = statusRepository.get() ?: return
        statusRepository.update(status.notebookId, memoId)
    }
}
