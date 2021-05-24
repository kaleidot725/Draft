package jp.kaleidot725.emomemo.usecase.get

class GetNotebookUseCase(private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository) {
    suspend fun execute(notebookId: Int): jp.kaleidot725.emomemo.data.entity.NotebookEntity? {
        return notebookRepository.getNoteBook(notebookId)
    }
}
