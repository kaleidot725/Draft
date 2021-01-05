package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity.Companion.UNSELECTED_MESSAGE
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class ReselectMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute() {
        val status = statusRepository.get() ?: return
        if (status.notebookId == StatusEntity.UNSELECTED_NOTEBOOK || status.memoId == StatusEntity.UNSELECTED_MEMO) return
        val firstMemo = memoRepository.getMemos(status.notebookId).firstOrNull() ?: return
        statusRepository.update(status.notebookId, firstMemo.id, UNSELECTED_MESSAGE)
    }
}
