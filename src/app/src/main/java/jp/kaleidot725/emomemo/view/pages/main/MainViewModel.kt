package jp.kaleidot725.emomemo.view.pages.main

import androidx.lifecycle.ViewModel
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.domain.usecase.MemoDetails
import jp.kaleidot725.emomemo.view.sample.SampleData
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel : ViewModel(), ContainerHost<MainState, MainSideEffect> {
    override val container: Container<MainState, MainSideEffect> = container(MainState())

    init {
        intent {
            reduce {
                state.copy(
                    notebooks = SampleData.notebookList,
                    selectedNotebook = SampleData.notebookList.first(),
                    memos = SampleData.memoDetailsList
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

    fun selectMemo(memo: MemoDetails) {
        intent {
            postSideEffect(MainSideEffect.NavigateMemoDetails)
        }
    }

    fun navigateAddMemo() {
        intent {
            postSideEffect(MainSideEffect.NavigateAddMemo)
        }
    }
}