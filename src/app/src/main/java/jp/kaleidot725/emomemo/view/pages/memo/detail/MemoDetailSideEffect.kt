package jp.kaleidot725.emomemo.view.pages.memo.detail

sealed class MemoDetailSideEffect {
    object Back : MemoDetailSideEffect()
    data class DeleteMemo(val memoId: Int) : MemoDetailSideEffect()
}
