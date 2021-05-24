package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.entity.StatusEntity
import jp.kaleidot725.emomemo.data.repository.NotebookRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class ReselectNotebookUseCase(
    private val statusRepository: StatusRepository,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute() {
        val firstNotebook = notebookRepository.getAll().firstOrNull() ?: return
        statusRepository.update(
            firstNotebook.id,
            StatusEntity.UNSELECTED_NOTEBOOK,
            StatusEntity.UNSELECTED_MESSAGE
        )
    }
}
