package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.repository.MemoStatusRepository

class GetMemoUseCase(private val memoStatusRepository: MemoStatusRepository) {
    suspend fun execute(memoId: Int): jp.kaleidot725.emomemo.data.view.MemoStatusView? {
        return memoStatusRepository.getMemoByMemoId(memoId)
    }
}
