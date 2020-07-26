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
            notebookRepository.insert(DEFAULT1_NOTEBOOK)
            notebookRepository.insert(DEFAULT2_NOTEBOOK)
        }
    }

    private suspend fun setupAppStatus() {
        appStatus.notebookId = notebookRepository.getAll().first().id
    }

    companion object {
        private val DEFAULT1_NOTEBOOK = NotebookEntity.create("デフォルト1")
        private val DEFAULT2_NOTEBOOK = NotebookEntity.create("デフォルト2")
    }
}
