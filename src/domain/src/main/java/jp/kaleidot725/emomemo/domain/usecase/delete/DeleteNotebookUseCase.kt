package jp.kaleidot725.emomemo.domain.usecase.delete

import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.data.repository.NotebookRepository

class DeleteNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(notebook: NotebookEntity) {
        notebookRepository.delete(notebook)
    }
}
