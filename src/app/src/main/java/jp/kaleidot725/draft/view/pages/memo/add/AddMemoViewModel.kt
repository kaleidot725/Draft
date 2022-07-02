package jp.kaleidot725.draft.view.pages.memo.add

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.create.CreateMemoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class AddMemoViewModel(
    private val notebookId: Long,
    private val createMemoUseCase: CreateMemoUseCase
) : ViewModel(), ContainerHost<AddMemoState, AddMemoSideEffect> {
    override val container: Container<AddMemoState, AddMemoSideEffect> = container(AddMemoState())

    fun updateMemoTitle(memoTitle: String) = intent {
        reduce {
            state.copy(memoTitle = memoTitle)
        }
    }

    fun ok() = intent {
        val newMemoId = createMemoUseCase.execute(notebookId, state.memoTitle) ?: return@intent
        postSideEffect(AddMemoSideEffect.NavigateMemo(newMemoId))
    }

    fun cancel() = intent {
        postSideEffect(AddMemoSideEffect.Close)
    }
}
