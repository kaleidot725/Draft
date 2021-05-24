package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MEMO
import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MESSAGE
import jp.kaleidot725.emomemo.data.repository.NotebookRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class SelectNotebookUseCase(
    private val statusRepository: StatusRepository,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(notebookId: Int) {
        statusRepository.update(notebookId, UNSELECTED_MEMO, UNSELECTED_MESSAGE)
    }
}
