package jp.kaleidot725.emomemo.view.pages.notebook.add

import androidx.lifecycle.ViewModel
import jp.kaleidot725.emomemo.domain.usecase.create.CreateNotebookUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddNotebookViewModel(
    private val createNotebookUseCase: CreateNotebookUseCase
) : ViewModel(), ContainerHost<AddNotebookState, AddNotebookSideEffect> {
    override val container: Container<AddNotebookState, AddNotebookSideEffect> = container(AddNotebookState())

    fun updateNotebookTitle(notebookTitle: String) {
        intent {
            reduce {
                state.copy(notebookTitle = notebookTitle)
            }
        }
    }

    fun ok() {
        intent {
            createNotebookUseCase.execute(state.notebookTitle)
            postSideEffect(AddNotebookSideEffect.Close)
        }
    }

    fun cancel() {
        intent {
            postSideEffect(AddNotebookSideEffect.Close)
        }
    }
}
