package jp.kaleidot725.draft.view.pages.memo.add

sealed class AddMemoSideEffect {
    data class NavigateMemo(val memoId: Long) : AddMemoSideEffect()
    object Close : AddMemoSideEffect()
}
