package jp.kaleidot725.draft.view.pages.notebook.edit

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.get.GetNotebookUseCase
import jp.kaleidot725.draft.domain.usecase.update.UpdateNotebookUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class EditNotebookViewModel(
    private val notebookId: Long,
    private val getNotebookUseCase: GetNotebookUseCase,
    private val updateNotebookUseCase: UpdateNotebookUseCase
) : ViewModel(), ContainerHost<EditNotebookState, EditNotebookSideEffect> {
    override val container: Container<EditNotebookState, EditNotebookSideEffect> = container(EditNotebookState())

    init {
        intent {
            val notebook = getNotebookUseCase.execute(notebookId) ?: return@intent
            reduce { state.copy(notebook = notebook, notebookTitle = notebook.title) }
        }
    }

    fun ok() = intent {
        state.notebook?.let { notebook ->
            updateNotebookUseCase.execute(notebook.copy(title = state.notebookTitle))
            postSideEffect(EditNotebookSideEffect.Close)
        }
    }

    fun cancel() = intent {
        postSideEffect(EditNotebookSideEffect.Close)
    }

    fun updateNotebookTitle(notebookTitle: String) = intent {
        reduce {
            state.copy(notebookTitle = notebookTitle)
        }
    }
}