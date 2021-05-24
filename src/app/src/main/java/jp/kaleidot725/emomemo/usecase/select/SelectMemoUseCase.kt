package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MESSAGE

class SelectMemoUseCase(private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository) {
    suspend fun execute(memoId: Int) {
        val status = statusRepository.get() ?: return
        statusRepository.update(status.notebookId, memoId, UNSELECTED_MESSAGE)
    }
}
