package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository

class UpdateMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoId: Int, notebookId: Int, title: String) {
        val new = MemoEntity(memoId, notebookId, title)
        memoRepository.update(new)
    }
}
