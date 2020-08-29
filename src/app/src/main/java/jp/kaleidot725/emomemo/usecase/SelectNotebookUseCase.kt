package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class SelectNotebookUseCase(
    private val statusRepository: StatusRepository,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(notebookId: Int) {
        statusRepository.update(notebookId, StatusEntity.UNSELECTED_NOTEBOOK)
    }
}
