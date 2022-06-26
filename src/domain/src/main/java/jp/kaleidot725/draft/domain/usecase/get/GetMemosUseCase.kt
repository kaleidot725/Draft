package jp.kaleidot725.draft.domain.usecase.get

import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.data.repository.MemoRepository

class GetMemosUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(notebookId: Long): List<MemoEntity> {
        return memoRepository.getMemosByNotebookId(notebookId)
    }
}
