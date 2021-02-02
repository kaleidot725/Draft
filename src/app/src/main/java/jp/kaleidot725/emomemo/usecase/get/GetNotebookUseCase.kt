package jp.kaleidot725.emomemo.usecase.get

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class GetNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(notebookId: Int): NotebookEntity? {
        return notebookRepository.getNoteBook(notebookId)
    }
}
