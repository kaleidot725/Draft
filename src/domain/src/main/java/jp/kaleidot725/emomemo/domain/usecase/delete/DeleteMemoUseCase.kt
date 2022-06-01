package jp.kaleidot725.emomemo.domain.usecase.delete

import jp.kaleidot725.emomemo.data.repository.MemoRepository

class DeleteMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoId: Long) {
        memoRepository.delete(listOf(memoId))
    }
}
