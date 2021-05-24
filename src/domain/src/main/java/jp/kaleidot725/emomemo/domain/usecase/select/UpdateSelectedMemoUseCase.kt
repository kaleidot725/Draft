package jp.kaleidot725.emomemo.domain.usecase.select

class UpdateSelectedMemoUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val memoRepository: jp.kaleidot725.emomemo.data.repository.MemoRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        val new = jp.kaleidot725.emomemo.data.entity.MemoEntity(status.memoId, status.notebookId, title)
        memoRepository.update(new)
    }
}
