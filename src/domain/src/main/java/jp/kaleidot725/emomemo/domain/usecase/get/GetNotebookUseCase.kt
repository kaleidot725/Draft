package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.data.repository.NotebookRepository

class GetNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(notebookId: Int): NotebookEntity? {
        return notebookRepository.getNoteBook(notebookId)
    }
}
