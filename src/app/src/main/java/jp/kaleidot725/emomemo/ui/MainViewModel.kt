package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.usecase.GetNotebookUseCase
import jp.kaleidot725.emomemo.usecase.ObserveStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectNotebookUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getNotebookUseCase: GetNotebookUseCase,
    private val selectNotebookUseCase: SelectNotebookUseCase,
    private val observeStatusUseCase: ObserveStatusUseCase
) : ViewModel() {
    val notebooks: LiveData<List<NotebookEntity>> = getNotebookUseCase.execute()

    private val _selectedNotebook: MutableLiveData<NotebookEntity> = MutableLiveData()
    val selectedNotebook: LiveData<NotebookEntity> = _selectedNotebook

    init {
        observeStatusUseCase.execute {
            updateSelectedNotebook(it)
        }
    }

    override fun onCleared() {
        observeStatusUseCase.dispose()
    }

    fun selectNotebook(notebook: NotebookEntity) {
        viewModelScope.launch {
            selectNotebookUseCase.execute(notebook.id)
        }
    }

    private fun updateSelectedNotebook(status: StatusEntity?) {
        status ?: return
        viewModelScope.launch {
            _selectedNotebook.value = getNotebookUseCase.execute(status.notebookId)
        }
    }
}

