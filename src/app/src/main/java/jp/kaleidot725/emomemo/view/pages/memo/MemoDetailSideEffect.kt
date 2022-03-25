package jp.kaleidot725.emomemo.view.pages.memo

sealed class MemoDetailSideEffect {
    object Back : MemoDetailSideEffect()
    data class DeleteMemo(val memoId: Int) : MemoDetailSideEffect()
}