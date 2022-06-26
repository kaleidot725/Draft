package jp.kaleidot725.draft.domain.usecase.get

import jp.kaleidot725.draft.data.entity.NotebookEntity
import jp.kaleidot725.draft.data.repository.NotebookRepository

class GetNotebooksUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(): List<NotebookEntity> {
        return notebookRepository.getAll()
    }
}
