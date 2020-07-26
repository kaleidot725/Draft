package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.AppStatus
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class DatabaseInitializeUsecase(
    private val appStatus: AppStatus,
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute() {
        createDefaultNotebook()
        setupAppStatus()
    }

    private suspend fun createDefaultNotebook() {
        if (notebookRepository.getAll().count() <= 0) {
            notebookRepository.insert(DEFAULT_NOTEBOOK)
        }
    }

    private suspend fun setupAppStatus() {
        appStatus.notebookId = notebookRepository.getAll().first().id
    }

    companion object {
        private val DEFAULT_NOTEBOOK = NotebookEntity.create("デフォルト")
    }
}
