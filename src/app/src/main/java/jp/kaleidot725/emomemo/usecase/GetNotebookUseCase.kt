package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository

class GetNotebookUseCase(private val notebookRepository: NotebookRepository) {
    fun execute(): LiveData<List<NotebookEntity>> {
        return notebookRepository.getAllLiveData()
    }
}
