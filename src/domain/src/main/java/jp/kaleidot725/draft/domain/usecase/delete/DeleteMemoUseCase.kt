package jp.kaleidot725.draft.domain.usecase.delete

import jp.kaleidot725.draft.data.repository.MemoRepository

class DeleteMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoId: Long) {
        memoRepository.delete(listOf(memoId))
    }
}
