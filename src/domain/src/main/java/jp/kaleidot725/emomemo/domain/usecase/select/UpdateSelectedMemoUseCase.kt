package jp.kaleidot725.emomemo.domain.usecase.select

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.repository.MemoRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class UpdateSelectedMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        val new = MemoEntity(status.memoId, status.notebookId, title)
        memoRepository.update(new)
    }
}
