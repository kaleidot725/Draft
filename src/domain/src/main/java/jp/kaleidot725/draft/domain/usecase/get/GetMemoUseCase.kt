package jp.kaleidot725.draft.domain.usecase.get

import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.data.repository.MemoRepository

class GetMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoId: Long): MemoEntity {
        return memoRepository.getMemoByMemoId(memoId)
    }
}
