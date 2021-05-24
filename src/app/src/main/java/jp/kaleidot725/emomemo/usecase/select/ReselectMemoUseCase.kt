package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MESSAGE

class ReselectMemoUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val memoRepository: jp.kaleidot725.emomemo.data.repository.MemoRepository
) {
    suspend fun execute() {
        val status = statusRepository.get() ?: return
        if (status.notebookId == jp.kaleidot725.emomemo.data.entity.StatusEntity.UNSELECTED_NOTEBOOK || status.memoId == jp.kaleidot725.emomemo.data.entity.StatusEntity.UNSELECTED_MEMO) return
        val firstMemo = memoRepository.getMemos(status.notebookId).firstOrNull() ?: return
        statusRepository.update(status.notebookId, firstMemo.id, UNSELECTED_MESSAGE)
    }
}
