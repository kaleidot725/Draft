package jp.kaleidot725.emomemo.view.pages.notebook

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class AddNotebookViewModel() : ViewModel(), ContainerHost<AddNotebookState, AddNotebookSideEffect> {
    override val container: Container<AddNotebookState, AddNotebookSideEffect> = container(AddNotebookState())
}