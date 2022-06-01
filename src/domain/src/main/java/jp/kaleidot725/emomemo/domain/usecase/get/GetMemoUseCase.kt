package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.repository.MemoRepository

class GetMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoId: Long): MemoEntity {
        return memoRepository.getMemo(memoId)
    }
}
