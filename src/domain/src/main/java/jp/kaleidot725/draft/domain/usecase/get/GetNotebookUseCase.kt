package jp.kaleidot725.draft.domain.usecase.get

import jp.kaleidot725.draft.data.entity.NotebookEntity
import jp.kaleidot725.draft.data.repository.NotebookRepository

class GetNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(notebookId: Long): NotebookEntity? {
        return notebookRepository.getNoteBook(notebookId)
    }
}
