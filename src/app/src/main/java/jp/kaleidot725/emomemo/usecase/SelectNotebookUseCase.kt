package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class SelectNotebookUseCase(private val statusRepository: StatusRepository) {
    suspend fun execute(notebookId: Int) {
        statusRepository.update(notebookId, 0)
    }
}
