package jp.kaleidot725.draft.view.pages.memo.detail

sealed class MemoDetailSideEffect {
    object Back : MemoDetailSideEffect()
    data class DeleteMemo(val memoId: Long) : MemoDetailSideEffect()
}
