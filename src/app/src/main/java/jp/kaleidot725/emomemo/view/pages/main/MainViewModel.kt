package jp.kaleidot725.emomemo.view.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebooksUseCase
import jp.kaleidot725.emomemo.view.sample.SampleData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    val getNotebooksUseCase: GetNotebooksUseCase
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

        viewModelScope.launch {
            getNotebooksUseCase.execute().collectLatest {
                intent {
                    reduce {
                        state.copy(notebooks = it)
                    }
                }
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