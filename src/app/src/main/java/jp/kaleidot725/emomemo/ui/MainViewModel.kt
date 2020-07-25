package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val notebookRepository: NotebookRepository) : ViewModel() {
    val notebooks: LiveData<List<NotebookEntity>> = liveData(Dispatchers.IO) {
        emit(notebookRepository.getAll())
    }
}
