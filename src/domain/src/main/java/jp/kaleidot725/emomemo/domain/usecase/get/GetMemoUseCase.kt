package jp.kaleidot725.emomemo.domain.usecase.get

class GetMemoUseCase(private val memoStatusRepository: jp.kaleidot725.emomemo.data.repository.MemoStatusRepository) {
    suspend fun execute(memoId: Int): jp.kaleidot725.emomemo.data.view.MemoStatusView? {
        return memoStatusRepository.getMemoByMemoId(memoId)
    }
}
