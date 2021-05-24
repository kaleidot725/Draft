package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MEMO
import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MESSAGE

class SelectNotebookUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository
) {
    suspend fun execute(notebookId: Int) {
        statusRepository.update(notebookId, UNSELECTED_MEMO, UNSELECTED_MESSAGE)
    }
}
