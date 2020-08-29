package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class DatabaseInitializeUsecase(
    private val statusRepository: StatusRepository,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute() {
        initStatus()
        initNotebook()
    }

    private suspend fun initStatus() {
        val status = statusRepository.get()
        if (status == null) {
            val notebook = notebookRepository.getAll().first()
            statusRepository.update(notebook.id, 0)
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
