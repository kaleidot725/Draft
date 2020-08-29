package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class InitializeDataBaseUseCase(
    private val statusRepository: StatusRepository,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute() {
        initNotebook()
        initStatus()
    }

    private suspend fun initStatus() {
        val status = statusRepository.get()
        if (status == null) {
            val notebook = notebookRepository.getAll().first()
            statusRepository.update(notebook.id, StatusEntity.UNSELECTED_MEMO)
        }
    }

    private suspend fun initNotebook() {
        if (notebookRepository.getAll().count() <= 0) {
            notebookRepository.insert(DEFAULT_NOTEBOOK)
        }
    }

    companion object {
        private val DEFAULT_NOTEBOOK = NotebookEntity.create("デフォルト")
    }
}
