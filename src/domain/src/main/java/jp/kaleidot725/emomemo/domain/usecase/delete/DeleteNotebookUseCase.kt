package jp.kaleidot725.emomemo.domain.usecase.delete

import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MEMO
import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_MESSAGE
import jp.kaleidot725.emomemo.data.entity.StatusEntity.Companion.UNSELECTED_NOTEBOOK

class DeleteNotebookUseCase(
    private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository,
    private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository
) {
    suspend fun execute(notebook: jp.kaleidot725.emomemo.data.entity.NotebookEntity) {
        notebookRepository.delete(notebook)
        reselectNotebook(notebook)
    }

    private suspend fun reselectNotebook(deleteItem: jp.kaleidot725.emomemo.data.entity.NotebookEntity) {
        val oldStatus = statusRepository.get()
        if (deleteItem.id == oldStatus?.notebookId) {
            val notebooks = notebookRepository.getAll()
            if (notebooks.isNotEmpty()) {
                statusRepository.update(notebooks.first().id, UNSELECTED_MEMO, UNSELECTED_MESSAGE)
            } else {
                statusRepository.update(UNSELECTED_NOTEBOOK, UNSELECTED_MEMO, UNSELECTED_MESSAGE)
            }
        }
    }
}
