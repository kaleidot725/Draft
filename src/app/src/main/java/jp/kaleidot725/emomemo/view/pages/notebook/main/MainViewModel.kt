package jp.kaleidot725.emomemo.view.pages.notebook.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemosFlowUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebooksFlowUseCase
import jp.kaleidot725.emomemo.domain.usecase.update.UpdateNotebookUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val getNotebooksUseCase: GetNotebooksFlowUseCase,
    private val getMemosFlowUseCase: GetMemosFlowUseCase,
    private val updateNotebookUseCase: UpdateNotebookUseCase
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        observeNotebooks()
    }

    fun createNotebook() {
        intent {
            postSideEffect(MainSideEffect.NavigateAddNotebook)
        }
    }

    fun deleteNotebook() {
        intent {
            val notebook = state.selectedNotebook ?: return@intent
            postSideEffect(MainSideEffect.NavigateBottomSheet(notebook.id))
        }
    }

    fun selectNotebook(notebook: NotebookEntity) {
        intent {
            reduce { state.copy(selectedNotebook = notebook) }
            observeMemos(notebook)
        }
    }

    fun createMemo() {
        intent {
            state.selectedNotebook?.let { notebook ->
                postSideEffect(MainSideEffect.NavigateAddMemo(notebook.id))
            }
        }
    }

    fun selectMemo(memo: MemoEntity) {
        intent {
            postSideEffect(MainSideEffect.NavigateMemoDetails(memo.id))
        }
    }

    private var notebooksJob: Job? = null
    private fun observeNotebooks() {
        notebooksJob?.cancel()
        notebooksJob = viewModelScope.launch {
            getNotebooksUseCase.execute().collectLatest {
                intent {
                    val selectedNotebook = if (it.contains(state.selectedNotebook)) state.selectedNotebook else it.firstOrNull()
                    reduce { state.copy(initialized = true, notebooks = it, selectedNotebook = selectedNotebook) }
                    if (selectedNotebook != null) observeMemos(selectedNotebook)
                }
            }
        }
    }

    private var memosJob: Job? = null
    private fun observeMemos(notebook: NotebookEntity) {
        memosJob?.cancel()
        memosJob = viewModelScope.launch {
            getMemosFlowUseCase.execute(notebook.id).collectLatest {
                intent { reduce { state.copy(initialized = true, memos = it) } }
            }
        }
    }
}
