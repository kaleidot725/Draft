package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class ReselectNotebookUseCase(
    private val statusRepository: StatusRepository,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute() {
        val firstNotebook = notebookRepository.getAll().firstOrNull() ?: return
        statusRepository.update(firstNotebook.id, StatusEntity.UNSELECTED_NOTEBOOK, StatusEntity.UNSELECTED_MESSAGE)
    }
}
