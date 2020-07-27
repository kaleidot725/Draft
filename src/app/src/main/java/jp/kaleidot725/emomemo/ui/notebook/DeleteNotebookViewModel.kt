package jp.kaleidot725.emomemo.ui.notebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteNotebookViewModel(private val notebookRepository: NotebookRepository) : ViewModel() {
    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event

    private val _notebooks: MutableLiveData<List<NotebookEntity>> = MutableLiveData()
    val notebookTitles: LiveData<List<String>> = _notebooks.map { notebooks ->
        notebooks.map { it.title }
    }

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            val notebooks = notebookRepository.getAll()
            withContext(Dispatchers.Main) {
                _notebooks.value = notebooks
            }
        }
    }

    fun success() {
        _event.postValue(NavEvent.Success(_notebooks.value!!.first()))
    }

    fun cancel() {
        _event.postValue(NavEvent.Cancel)
    }

    fun onNotebookSelected(position: Int) {

    }

    sealed class NavEvent {
        data class Success(val notebook: NotebookEntity) : NavEvent()
        object Cancel : NavEvent()
    }
}
