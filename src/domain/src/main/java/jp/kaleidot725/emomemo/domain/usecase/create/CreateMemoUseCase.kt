package jp.kaleidot725.emomemo.domain.usecase.create

import jp.kaleidot725.emomemo.data.repository.MemoRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class CreateMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        memoRepository.insert(jp.kaleidot725.emomemo.data.entity.MemoEntity.create(status.notebookId, title))
    }
}
