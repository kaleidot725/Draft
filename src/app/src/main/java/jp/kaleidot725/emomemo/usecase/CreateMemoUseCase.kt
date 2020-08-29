package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class CreateMemoUseCase(
    private val statusRepository: StatusRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        memoRepository.insert(MemoEntity.create(status.notebookId, title))
    }
}
