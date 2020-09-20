package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class GetMemoUseCase(private val memoStatusRepository: MemoStatusRepository) {
    suspend fun execute(memoId: Int): MemoStatusView? {
        return memoStatusRepository.getMemoByMemoId(memoId)
    }
}
