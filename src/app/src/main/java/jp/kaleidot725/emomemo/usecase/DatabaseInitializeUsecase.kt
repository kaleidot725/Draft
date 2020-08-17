package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class DatabaseInitializeUsecase(
    private val notebookRepository: NotebookRepository
) {
    suspend fun execute() {
        createDefaultNotebook()
    }

    private suspend fun createDefaultNotebook() {
        if (notebookRepository.getAll().count() <= 0) {
            notebookRepository.insert(DEFAULT_NOTEBOOK)
        }
    }

    companion object {
        private val DEFAULT_NOTEBOOK = NotebookEntity.create("デフォルト")
    }
}
