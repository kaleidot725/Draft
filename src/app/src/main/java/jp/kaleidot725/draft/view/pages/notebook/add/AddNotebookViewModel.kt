package jp.kaleidot725.draft.view.pages.notebook.add

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.create.CreateNotebookUseCase
import jp.kaleidot725.draft.view.pages.memo.add.AddMemoSideEffect
import jp.kaleidot725.draft.view.pages.memo.add.AddMemoState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddNotebookViewModel(
    private val createNotebookUseCase: CreateNotebookUseCase
) : ViewModel(), ContainerHost<AddMemoState, AddMemoSideEffect> {
    override val container: Container<AddMemoState, AddMemoSideEffect> = container(AddMemoState())

    fun updateNotebookTitle(notebookTitle: String) {
        intent {
            reduce {
                state.copy(memoTitle = notebookTitle)
            }
        }
    }

    fun ok() {
        intent {
            createNotebookUseCase.execute(state.memoTitle)
            postSideEffect(AddMemoSideEffect.Close)
        }
    }

    fun cancel() {
        intent {
            postSideEffect(AddMemoSideEffect.Close)
        }
    }
}
