package jp.kaleidot725.emomemo.usecase.delete

class DeleteMemoUseCase(private val memoRepository: jp.kaleidot725.emomemo.data.repository.MemoRepository) {
    suspend fun execute(memoId: Int) {
        memoRepository.delete(listOf(memoId))
    }
}
