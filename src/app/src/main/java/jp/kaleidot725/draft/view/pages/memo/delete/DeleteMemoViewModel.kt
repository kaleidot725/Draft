package jp.kaleidot725.draft.view.pages.memo.delete

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.delete.DeleteMemoUseCase
import jp.kaleidot725.draft.domain.usecase.get.GetMemoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DeleteMemoViewModel(
    private val memoId: Long,
    private val getMemoUseCase: GetMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase
) : ViewModel(), ContainerHost<DeleteMemoState, DeleteMemoSideEffect> {
    override val container: Container<DeleteMemoState, DeleteMemoSideEffect> = container(DeleteMemoState())

    init {
        intent {
            val memo = getMemoUseCase.execute(memoId)
            reduce { DeleteMemoState(memo) }
        }
    }

    fun ok() {
        intent {
            deleteMemoUseCase.execute(memoId)
            postSideEffect(DeleteMemoSideEffect.BackHome)
        }
    }

    fun cancel() {
        intent {
            postSideEffect(DeleteMemoSideEffect.Close)
        }
    }
}
