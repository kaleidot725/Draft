package jp.kaleidot725.emomemo.domain.usecase.create

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.repository.MemoRepository
import jp.kaleidot725.emomemo.data.repository.NotebookRepository

class CreateMemoUseCase(
    private val notebookRepository: NotebookRepository,
    private val memoRepository: MemoRepository
) {
    suspend fun execute(notebookId: Long): Long? {
        val notebook = notebookRepository.getNoteBook(notebookId) ?: return null
        return memoRepository.insert(MemoEntity.create(notebook.id, "TITLE", ""))
    }
}
