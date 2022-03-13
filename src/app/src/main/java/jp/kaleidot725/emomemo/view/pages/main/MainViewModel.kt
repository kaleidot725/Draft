package jp.kaleidot725.emomemo.view.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemosUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebooksFlowUseCase
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
    private val getMemosUseCase: GetMemosUseCase
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        observeNotebooks()
    }

    fun navigateAddNotebook() {
        intent {
            postSideEffect(MainSideEffect.NavigateAddNotebook)
        }
    }

    fun navigateRemoveNotebook() {
        intent {
            postSideEffect(MainSideEffect.NavigateRemoveNotebook)
        }
    }

    fun selectNotebook(notebook: NotebookEntity) {
        intent {
            val memos = getMemosUseCase.execute(notebook.id)
            reduce { state.copy(selectedNotebook = notebook, memos = memos) }
        }
    }

    fun createMemo() {
        intent {
            postSideEffect(MainSideEffect.NavigateMemoDetails)
        }
    }

    fun selectMemo(memo: MemoEntity) {
        intent {
            postSideEffect(MainSideEffect.NavigateMemoDetails)
        }
    }

    private fun observeNotebooks() {
        viewModelScope.launch {
            getNotebooksUseCase.execute().collectLatest {
                intent {
                    val selectedNotebook = if (it.contains(state.selectedNotebook)) state.selectedNotebook else it.firstOrNull()
                    val memos = if (selectedNotebook != null) getMemosUseCase.execute(selectedNotebook.id) else emptyList()
                    reduce { state.copy(notebooks = it, selectedNotebook = selectedNotebook, memos = memos) }
                }
            }
        }
    }
}