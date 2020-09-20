package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.GetMemoUseCase
import jp.kaleidot725.emomemo.usecase.GetNotebookUseCase
import jp.kaleidot725.emomemo.usecase.GetNotebooksUseCase
import jp.kaleidot725.emomemo.usecase.GetStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectNotebookUseCase
import kotlinx.coroutines.launch

data class NotebookWithStatus(
    val notebooks: List<NotebookEntity>,
    val selectedNotebook: NotebookEntity?,
    val selectedMemo: MemoStatusView?
)

class MainViewModel(
    private val getNotebookUseCase: GetNotebookUseCase,
    private val getNotebooksUseCase: GetNotebooksUseCase,
    private val getStatusUseCase: GetStatusUseCase,
    private val getMemoUseCase: GetMemoUseCase,
    private val selectNotebookUseCase: SelectNotebookUseCase
) : ViewModel() {
    private val refresh: MutableLiveData<Unit> = MutableLiveData(Unit)
    val notebooksWithStatus: LiveData<NotebookWithStatus> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext) {
            val notebooks = getNotebooksUseCase.execute()
            val status = getStatusUseCase.execute() ?: StatusEntity()
            val selectedNotebook = getNotebookUseCase.execute(status.notebookId)
            val selectedMemo = getMemoUseCase.execute(status.memoId)
            emit(NotebookWithStatus(notebooks, selectedNotebook, selectedMemo))
        }
    }

    fun refresh() {
        refresh.value = Unit
    }

    fun selectNotebook(notebook: NotebookEntity) {
        viewModelScope.launch {
            selectNotebookUseCase.execute(notebook.id)
            refresh.value = Unit
        }
    }
}
