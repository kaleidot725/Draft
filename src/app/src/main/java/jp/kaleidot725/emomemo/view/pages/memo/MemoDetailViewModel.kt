package jp.kaleidot725.emomemo.view.pages.memo

import androidx.lifecycle.ViewModel
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.update.UpdateMemoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MemoDetailViewModel(
    val memoId: Int,
    private val getMemoUseCase: GetMemoUseCase,
    private val updateMemoUseCase: UpdateMemoUseCase
) : ViewModel(),
    ContainerHost<MemoDetailState, MemoDetailSideEffect> {
    override val container: Container<MemoDetailState, MemoDetailSideEffect> = container(MemoDetailState())

    init {
        intent {
            val memo = getMemoUseCase.execute(memoId)
            reduce { MemoDetailState(memo) }
        }
    }

    fun updateTitle(title: String) {
        intent {
            val newMemo = state.memoEntity?.copy(title = title)
            if (newMemo != null) {
                updateMemoUseCase.execute(newMemo)
                reduce { state.copy(memoEntity = newMemo) }
            }
        }
    }

    fun updateContent(content: String) {
        intent {
            val newMemo = state.memoEntity?.copy(content = content)
            if (newMemo != null) {
                updateMemoUseCase.execute(newMemo)
                reduce { state.copy(memoEntity = newMemo) }
            }
        }
    }

    fun back() {
        intent {
            postSideEffect(MemoDetailSideEffect.Back)
        }
    }

    fun deleteMemo() {
        intent {
            postSideEffect(MemoDetailSideEffect.DeleteMemo(state.memoEntity?.id ?: 0))
        }
    }
}
