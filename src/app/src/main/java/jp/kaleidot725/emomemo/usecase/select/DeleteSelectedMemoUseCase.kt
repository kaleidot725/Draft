package jp.kaleidot725.emomemo.usecase.select

import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class DeleteSelectedMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute() {
        val status = statusRepository.get() ?: return
        memoRepository.delete(listOf(status.memoId))
    }
}
