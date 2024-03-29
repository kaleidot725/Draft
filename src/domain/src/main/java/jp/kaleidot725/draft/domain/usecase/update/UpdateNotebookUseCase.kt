package jp.kaleidot725.draft.domain.usecase.update

import jp.kaleidot725.draft.data.entity.NotebookEntity
import jp.kaleidot725.draft.data.repository.NotebookRepository

class UpdateNotebookUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(notebookEntity: NotebookEntity) {
        notebookRepository.update(notebookEntity)
    }
}
