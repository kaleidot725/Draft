package jp.kaleidot725.emomemo.domain.usecase.update

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.repository.MemoRepository

class UpdateMemoUseCase(private val memoRepository: MemoRepository) {
    suspend fun execute(memoEntity: MemoEntity) {
        memoRepository.update(memoEntity)
    }
}
