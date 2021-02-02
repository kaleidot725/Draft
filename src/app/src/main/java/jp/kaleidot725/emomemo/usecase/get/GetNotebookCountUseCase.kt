package jp.kaleidot725.emomemo.usecase.get

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class GetNotebookCountUseCase(private val notebookRepository: NotebookRepository) {
    fun execute(): LiveData<Int> {
        return notebookRepository.getNotebookCountLiveData()
    }
}
