package jp.kaleidot725.draft.domain.usecase.create

import jp.kaleidot725.draft.data.entity.NotebookEntity
import jp.kaleidot725.draft.data.repository.NotebookRepository

class CreateNotebookUseCase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(title: String) {
        notebookRepository.insert(NotebookEntity.create(title))
    }
}
