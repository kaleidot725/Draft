package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.domain.usecase.delete.DeleteNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebooksUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DeleteNotebookViewModel(
    private val getNotebooksUseCase: GetNotebooksUseCase,
    private val deleteNotebookUseCase: DeleteNotebookUseCase
) : ViewModel(), ContainerHost<DeleteNotebookState, DeleteNotebookSideEffect> {
    override val container: Container<DeleteNotebookState, DeleteNotebookSideEffect> = container(DeleteNotebookState())

    init {
        viewModelScope.launch {
            getNotebooksUseCase.execute().collectLatest {
                intent {
                    reduce {
                        val selectedNotebook = if (it.contains(state.selectedNotebook)) state.selectedNotebook else it.firstOrNull()
                        state.copy(notebooks = it, selectedNotebook = selectedNotebook)
                    }
                }
            }
        }
    }

    fun ok() {
        intent {
            val selectedNotebook = state.selectedNotebook
            if (selectedNotebook != null) {
                deleteNotebookUseCase.execute(selectedNotebook)
                postSideEffect(DeleteNotebookSideEffect.Close)
            }
        }
    }

    fun cancel() {
        intent {
            postSideEffect(DeleteNotebookSideEffect.Close)
        }
    }

    fun select(notebook: NotebookEntity) {
        intent {
            reduce {
                state.copy(selectedNotebook = notebook)
            }
        }
    }
}