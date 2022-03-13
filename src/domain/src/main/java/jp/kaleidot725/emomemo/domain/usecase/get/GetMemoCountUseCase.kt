package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.repository.MemoRepository
import kotlinx.coroutines.flow.Flow

class GetMemoCountUseCase(private val memoRepository: MemoRepository) {
    fun execute(notebookId: Int): Flow<Int> {
        return memoRepository.getMemoCountFlow(notebookId)
    }
}
