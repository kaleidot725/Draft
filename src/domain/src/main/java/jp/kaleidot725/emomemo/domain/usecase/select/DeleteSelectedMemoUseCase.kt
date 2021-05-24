package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.repository.MemoRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class DeleteSelectedMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute() {
        val status = statusRepository.get() ?: return
        memoRepository.delete(listOf(status.memoId))
    }
}
