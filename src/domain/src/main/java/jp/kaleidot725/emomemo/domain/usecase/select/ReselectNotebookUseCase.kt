package jp.kaleidot725.emomemo.domain.usecase.select

class ReselectNotebookUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository
) {
    suspend fun execute() {
        val firstNotebook = notebookRepository.getAll().firstOrNull() ?: return
        statusRepository.update(
            firstNotebook.id,
            jp.kaleidot725.emomemo.data.entity.StatusEntity.UNSELECTED_NOTEBOOK,
            jp.kaleidot725.emomemo.data.entity.StatusEntity.UNSELECTED_MESSAGE
        )
    }
}
