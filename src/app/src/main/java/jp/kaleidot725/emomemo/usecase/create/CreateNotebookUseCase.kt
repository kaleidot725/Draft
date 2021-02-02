package jp.kaleidot725.emomemo.usecase.create

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class CreateNotebookUseCase(
    private val notebookRepository: NotebookRepository,
    private val statusRepository: StatusRepository
) {
    suspend fun execute(title: String) {
        notebookRepository.insert(NotebookEntity.create(title))
        reselectNotebook()
    }

    private suspend fun reselectNotebook() {
        val oldStatus = statusRepository.get()
        if (oldStatus?.notebookId == StatusEntity.UNSELECTED_NOTEBOOK) {
            val notebooks = notebookRepository.getAll()
            if (notebooks.isNotEmpty()) {
                statusRepository.update(notebooks.first().id, StatusEntity.UNSELECTED_MEMO, StatusEntity.UNSELECTED_MESSAGE)
            }
        }
    }
}
