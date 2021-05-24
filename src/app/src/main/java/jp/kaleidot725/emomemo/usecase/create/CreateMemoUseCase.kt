package jp.kaleidot725.emomemo.usecase.create

class CreateMemoUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val memoRepository: jp.kaleidot725.emomemo.data.repository.MemoRepository
) {
    suspend fun execute(title: String) {
        val status = statusRepository.get() ?: return
        memoRepository.insert(jp.kaleidot725.emomemo.data.entity.MemoEntity.create(status.notebookId, title))
    }
}
