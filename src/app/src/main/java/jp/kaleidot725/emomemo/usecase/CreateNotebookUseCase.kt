package jp.kaleidot725.emomemo.usecase

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
        statusRepository.get().apply {
            if (this?.notebookId != StatusEntity.UNSELECTED_NOTEBOOK) {
                val notebook = notebookRepository.getAll().first()
                statusRepository.update(notebook.id, StatusEntity.UNSELECTED_MEMO)
            }
        }
    }
}
