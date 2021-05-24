package jp.kaleidot725.emomemo.usecase.create

class CreateNotebookUseCase(
    private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository,
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository
) {
    suspend fun execute(title: String) {
        notebookRepository.insert(jp.kaleidot725.emomemo.data.entity.NotebookEntity.create(title))
        reselectNotebook()
    }

    private suspend fun reselectNotebook() {
        val oldStatus = statusRepository.get()
        if (oldStatus?.notebookId == jp.kaleidot725.emomemo.data.entity.StatusEntity.UNSELECTED_NOTEBOOK) {
            val notebooks = notebookRepository.getAll()
            if (notebooks.isNotEmpty()) {
                statusRepository.update(
                    notebooks.first().id,
                    jp.kaleidot725.emomemo.data.entity.StatusEntity.UNSELECTED_MEMO,
                    jp.kaleidot725.emomemo.data.entity.StatusEntity.UNSELECTED_MESSAGE
                )
            }
        }
    }
}
