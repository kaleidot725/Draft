package jp.kaleidot725.emomemo.domain.usecase.update

import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.data.repository.NotebookRepository

class UpdateNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(notebookEntity: jp.kaleidot725.emomemo.data.entity.NotebookEntity, value: String) {
        val new = NotebookEntity(notebookEntity.id, value)
        notebookRepository.update(new)
    }
}
