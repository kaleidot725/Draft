package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class CreateNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(title: String) {
        notebookRepository.insert(NotebookEntity.create(title))
    }
}
