package jp.kaleidot725.draft.domain.usecase.delete

import jp.kaleidot725.draft.data.repository.MemoRepository
import jp.kaleidot725.draft.data.repository.NotebookRepository

class DeleteNotebookUseCase(
    private val memoRepository: MemoRepository,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute(notebookId: Long) {
        memoRepository.delete(memoRepository.getMemosByNotebookId(notebookId).map { it.id })
        notebookRepository.delete(listOf(notebookId))
    }
}
