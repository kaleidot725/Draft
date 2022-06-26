package jp.kaleidot725.draft.view.pages.notebook.main

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.data.entity.NotebookEntity
import jp.kaleidot725.draft.domain.usecase.get.GetMemosUseCase
import jp.kaleidot725.draft.domain.usecase.get.GetNotebooksUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val getNotebooksUseCase: GetNotebooksUseCase,
    private val getMemosFlowUseCase: GetMemosUseCase
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        refresh()
    }

    fun refresh() {
        intent {
            reduce { state.copy(isLoading = false) }
            val notebooks = getNotebooksUseCase.execute()
            val selectedNotebook = notebooks.firstOrNull { it.id == state.selectedNotebook?.id } ?: notebooks.firstOrNull()
            val memos = selectedNotebook?.let { getMemosFlowUseCase.execute(it.id) } ?: emptyList()
            reduce { state.copy(isLoading = false, notebooks = notebooks, selectedNotebook = selectedNotebook, memos = memos) }
        }
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
        }
        refresh()
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
}
