package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.repository.MemoRepository

class GetMemosUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(notebookId: Long): List<MemoEntity> {
        return memoRepository.getMemos(notebookId)
    }
}
