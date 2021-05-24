package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository
import jp.kaleidot725.emomemo.data.view.MemoStatusView

class GetSelectedMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoStatusRepository: MemoStatusRepository
) {
    suspend fun execute(): MemoStatusView? {
        val status = statusRepository.get() ?: return null
        return memoStatusRepository.getMemoByMemoId(status.memoId)
    }
}
