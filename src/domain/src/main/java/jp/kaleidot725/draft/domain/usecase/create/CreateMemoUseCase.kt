package jp.kaleidot725.draft.domain.usecase.create

import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.data.repository.MemoRepository
import jp.kaleidot725.draft.data.repository.NotebookRepository

class CreateMemoUseCase(
    private val notebookRepository: NotebookRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute(notebookId: Long, title: String): Long? {
        val notebook = notebookRepository.getNoteBook(notebookId) ?: return null
        return memoRepository.insert(MemoEntity.create(notebook.id, title, ""))
    }
}
