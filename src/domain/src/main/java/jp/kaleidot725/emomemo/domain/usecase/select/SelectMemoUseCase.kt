package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MESSAGE
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class SelectMemoUseCase(private val statusRepository: StatusRepository) {
    suspend fun execute(memoId: Int) {
        val status = statusRepository.get() ?: return
        statusRepository.update(status.notebookId, memoId, UNSELECTED_MESSAGE)
    }
}
