package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class DeleteNotebookViewModel() : ViewModel(), ContainerHost<DeleteNotebookState, DeleteNotebookSideEffect> {
    override val container: Container<DeleteNotebookState, DeleteNotebookSideEffect> = container(DeleteNotebookState())
}