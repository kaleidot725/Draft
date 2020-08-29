package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class DeleteNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(notebook: NotebookEntity) {
        notebookRepository.delete(notebook)
    }
}
