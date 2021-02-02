package jp.kaleidot725.emomemo.usecase.update

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class UpdateNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(notebookEntity: NotebookEntity, value: String) {
        val new = NotebookEntity(notebookEntity.id, value)
        notebookRepository.update(new)
    }
}
