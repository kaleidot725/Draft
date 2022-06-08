package jp.kaleidot725.emomemo.view.pages.memo.bottom

sealed class MemoBottomSheetSideEffect {
    data class NavigateDeleteMemo(val memoId: Long) : MemoBottomSheetSideEffect()
    data class NavigateEditMemo(val memoId: Long) : MemoBottomSheetSideEffect()
}
