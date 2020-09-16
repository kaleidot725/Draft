package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class DeleteMemosUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memos: List<MemoStatusView>) {
        memoRepository.delete(memos.map { it.id })
    }
}
