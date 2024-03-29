package jp.kaleidot725.draft.view.pages.notebook.delete

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.delete.DeleteNotebookUseCase
import jp.kaleidot725.draft.domain.usecase.get.GetNotebookUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DeleteNotebookViewModel(
    private val notebookId: Long,
    private val getNotebookUseCase: GetNotebookUseCase,
    private val deleteNotebookUseCase: DeleteNotebookUseCase
) : ViewModel(), ContainerHost<DeleteNotebookState, DeleteNotebookSideEffect> {
    override val container: Container<DeleteNotebookState, DeleteNotebookSideEffect> = container(DeleteNotebookState())

    init {
        intent {
            val notebook = getNotebookUseCase.execute(notebookId)
            reduce { DeleteNotebookState(notebook) }
        }
    }

    fun ok() = intent {
        deleteNotebookUseCase.execute(notebookId)
        postSideEffect(DeleteNotebookSideEffect.BackHome)
    }

    fun cancel() = intent {
        postSideEffect(DeleteNotebookSideEffect.Close)
    }
}
