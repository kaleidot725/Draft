package jp.kaleidot725.emomemo.usecase.get

import androidx.lifecycle.LiveData

class GetNotebooksUseCase(private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository) {
    suspend fun execute(): List<jp.kaleidot725.emomemo.data.entity.NotebookEntity> {
        return notebookRepository.getAll()
    }

    fun executeLiveData(): LiveData<List<jp.kaleidot725.emomemo.data.entity.NotebookEntity>> {
        return notebookRepository.getAllLiveData()
    }
}
