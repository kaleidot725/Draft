package jp.kaleidot725.emomemo.view.pages.main

import androidx.lifecycle.ViewModel
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.view.sample.SampleData
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
) : ViewModel(), ContainerHost<MainState, MainSideEffect> {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        intent {
            reduce {
                state.copy(
                    notebooks = SampleData.notebookList,
                    selectedNotebook = SampleData.notebookList.first(),
                    memos = SampleData.memoList
                )
            }
        }
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
            reduce {
                state.copy(
                    selectedNotebook = notebook
                )
            }
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
}