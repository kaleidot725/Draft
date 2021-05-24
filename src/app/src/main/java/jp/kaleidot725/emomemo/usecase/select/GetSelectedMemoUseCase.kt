package jp.kaleidot725.emomemo.usecase.select

class GetSelectedMemoUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val memoStatusRepository: jp.kaleidot725.emomemo.data.repository.MemoStatusRepository
) {
    suspend fun execute(): jp.kaleidot725.emomemo.data.view.MemoStatusView? {
        val status = statusRepository.get() ?: return null
        return memoStatusRepository.getMemoByMemoId(status.memoId)
    }
}
