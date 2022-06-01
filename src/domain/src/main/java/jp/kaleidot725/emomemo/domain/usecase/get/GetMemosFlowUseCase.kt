package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.repository.MemoRepository
import kotlinx.coroutines.flow.Flow

class GetMemosFlowUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(notebookId: Long): Flow<List<MemoEntity>> {
        return memoRepository.getMemosFlow(notebookId)
    }
}
