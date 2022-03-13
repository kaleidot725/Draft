package jp.kaleidot725.emomemo.domain.usecase.create

import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.data.repository.NotebookRepository

class CreateNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(title: String) {
        notebookRepository.insert(NotebookEntity.create(title))
    }
}
