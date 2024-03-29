package jp.kaleidot725.draft.view.pages.memo.detail

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.get.GetMemoUseCase
import jp.kaleidot725.draft.domain.usecase.update.UpdateMemoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MemoDetailViewModel(
    private val memoId: Long, private val getMemoUseCase: GetMemoUseCase, private val updateMemoUseCase: UpdateMemoUseCase
) : ViewModel(), ContainerHost<MemoDetailState, MemoDetailSideEffect> {
    override val container: Container<MemoDetailState, MemoDetailSideEffect> = container(MemoDetailState())

    fun refresh() = intent {
        val newEntity = getMemoUseCase.execute(memoId)
        reduce {
            state.copy(memoEntity = newEntity)
        }
    }

    fun saveMemo(content: String) = intent {
        val newEntity = state.memoEntity?.copy(content = content) ?: return@intent
        updateMemoUseCase.execute(newEntity)
        reduce { state.copy(memoEntity = newEntity) }
    }

    fun deleteMemo() = intent {
        val id = state.memoEntity?.id ?: -1
        postSideEffect(MemoDetailSideEffect.DeleteMemo(id))
    }

    fun back() = intent {
        postSideEffect(MemoDetailSideEffect.Back)
    }
}
