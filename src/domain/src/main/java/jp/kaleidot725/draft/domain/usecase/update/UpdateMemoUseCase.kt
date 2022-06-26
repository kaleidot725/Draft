package jp.kaleidot725.draft.domain.usecase.update

import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.data.repository.MemoRepository

class UpdateMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoEntity: MemoEntity) {
        memoRepository.update(memoEntity)
    }
}
