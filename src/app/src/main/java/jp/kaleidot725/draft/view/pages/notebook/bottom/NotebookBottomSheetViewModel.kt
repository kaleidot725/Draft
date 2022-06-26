package jp.kaleidot725.draft.view.pages.notebook.bottom

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.get.GetNotebookUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class NotebookBottomSheetViewModel(
    private val notebookId: Long,
    private val getNotebookUseCase: GetNotebookUseCase
) : ViewModel(), ContainerHost<NotebookBottomSheetState, NotebookBottomSheetSideEffect> {
    override val container: Container<NotebookBottomSheetState, NotebookBottomSheetSideEffect> = container(NotebookBottomSheetState())

    init {
        intent {
            val notebook = getNotebookUseCase.execute(notebookId)
            reduce { NotebookBottomSheetState(notebook) }
        }
    }

    fun deleteNotebook() {
        intent {
            postSideEffect(NotebookBottomSheetSideEffect.NavigateDeleteNotebook(notebookId))
        }
    }

    fun editNotebook() {
        intent {
            postSideEffect(NotebookBottomSheetSideEffect.NavigateEditNotebook(notebookId))
        }
    }
}