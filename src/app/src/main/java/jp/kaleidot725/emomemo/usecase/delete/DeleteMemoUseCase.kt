package jp.kaleidot725.emomemo.usecase.delete

import jp.kaleidot725.emomemo.model.db.repository.MemoRepository

class DeleteMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoId: Int) {
        memoRepository.delete(listOf(memoId))
    }
}
