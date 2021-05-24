package jp.kaleidot725.emomemo.usecase.get

import androidx.lifecycle.LiveData

class GetNotebookCountUseCase(private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository) {
    fun execute(): LiveData<Int> {
        return notebookRepository.getNotebookCountLiveData()
    }
}
