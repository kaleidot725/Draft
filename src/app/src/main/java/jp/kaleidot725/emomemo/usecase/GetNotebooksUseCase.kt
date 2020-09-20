package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class GetNotebooksUseCase(private val notebookRepository: NotebookRepository) {
    suspend fun execute(): List<NotebookEntity> {
        return notebookRepository.getAll()
    }

    fun executeLiveData(): LiveData<List<NotebookEntity>> {
        return notebookRepository.getAllLiveData()
    }
}
