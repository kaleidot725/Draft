package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class GetSelectedMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoStatusRepository: MemoStatusRepository
) {
    suspend fun execute(): MemoStatusView? {
        val status = statusRepository.get() ?: return null
        return memoStatusRepository.getMemoByMemoId(status.memoId)
    }
}
