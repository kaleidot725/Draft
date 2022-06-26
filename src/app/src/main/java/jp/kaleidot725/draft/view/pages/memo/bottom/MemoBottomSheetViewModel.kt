package jp.kaleidot725.draft.view.pages.memo.bottom

import androidx.lifecycle.ViewModel
import jp.kaleidot725.draft.domain.usecase.get.GetMemoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MemoBottomSheetViewModel(
    private val memoId: Long,
    private val getMemoUseCase: GetMemoUseCase
) : ViewModel(), ContainerHost<MemoBottomSheetState, MemoBottomSheetSideEffect> {
    override val container: Container<MemoBottomSheetState, MemoBottomSheetSideEffect> = container(MemoBottomSheetState())

    init {
        intent {
            val memo = getMemoUseCase.execute(memoId)
            reduce { MemoBottomSheetState(memo) }
        }
    }

    fun deleteMemo() {
        intent {
            postSideEffect(MemoBottomSheetSideEffect.NavigateDeleteMemo(memoId))
        }
    }

    fun editMemo() {
        intent {
            postSideEffect(MemoBottomSheetSideEffect.NavigateEditMemo(memoId))
        }
    }
}