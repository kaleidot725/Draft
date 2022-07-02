package jp.kaleidot725.draft.view.pages.memo.edit

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.get.GetMemoUseCase
import jp.kaleidot725.draft.domain.usecase.update.UpdateMemoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class EditMemoViewModel(
    private val memoId: Long, private val getMemoUseCase: GetMemoUseCase, private val updateMemoUseCase: UpdateMemoUseCase
) : ViewModel(), ContainerHost<EditMemoState, EditMemoSideEffect> {
    override val container: Container<EditMemoState, EditMemoSideEffect> = container(EditMemoState())

    init {
        intent {
            val memo = getMemoUseCase.execute(memoId) ?: return@intent
            reduce { state.copy(memo = memo, memoTitle = memo.title) }
        }
    }

    fun ok() = intent {
        state.memo?.let { memo ->
            updateMemoUseCase.execute(memo.copy(title = state.memoTitle))
            postSideEffect(EditMemoSideEffect.Close)
        }
    }

    fun cancel() = intent {
        postSideEffect(EditMemoSideEffect.Close)
    }

    fun updateMemoTitle(memoTitle: String) = intent {
        reduce {
            state.copy(memoTitle = memoTitle)
        }
    }
}