package jp.kaleidot725.emomemo.domain.usecase.select

class DeleteSelectedMemoUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val memoRepository: jp.kaleidot725.emomemo.data.repository.MemoRepository
) {
    suspend fun execute() {
        val status = statusRepository.get() ?: return
        memoRepository.delete(listOf(status.memoId))
    }
}
