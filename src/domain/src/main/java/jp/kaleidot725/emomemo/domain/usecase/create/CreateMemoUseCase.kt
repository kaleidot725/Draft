package jp.kaleidot725.emomemo.domain.usecase.create

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.data.repository.MemoRepository

class CreateMemoUseCase(
    private val memoRepository: MemoRepository
) {
    suspend fun execute(notebookEntity: NotebookEntity): MemoEntity {
        val newMemo = MemoEntity.create(notebookEntity.id, "NEW MEMO", "NEW CONTENT")
        val newId = memoRepository.insert(newMemo)
        return newMemo.copy(id = newId.toInt())
    }
}
