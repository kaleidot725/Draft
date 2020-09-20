package jp.kaleidot725.emomemo.ui.notebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.usecase.DeleteNotebookUseCase
import jp.kaleidot725.emomemo.usecase.GetNotebooksUseCase
import kotlinx.coroutines.launch

class DeleteNotebookViewModel(
    private val getNotebooksUseCase: GetNotebooksUseCase,
    private val deleteNotebookUseCase: DeleteNotebookUseCase
) :
    ViewModel() {
    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event

    private val _notebooks: LiveData<List<NotebookEntity>> = getNotebooksUseCase.executeLiveData()
    val notebook: LiveData<List<String>> = _notebooks.map { list -> list.map { note -> note.title } }
    private var selectedNotebook: NotebookEntity? = null

    fun success() {
        viewModelScope.launch {
            selectedNotebook?.let {
                deleteNotebookUseCase.execute(it)
                _event.value = NavEvent.Success
            }
        }
    }

    fun cancel() {
        _event.value = NavEvent.Cancel
    }

    fun onNotebookSelected(position: Int) {
        selectedNotebook = _notebooks.value?.getOrNull(position)
    }

    sealed class NavEvent {
        object Success : NavEvent()
        object Cancel : NavEvent()
    }
}
