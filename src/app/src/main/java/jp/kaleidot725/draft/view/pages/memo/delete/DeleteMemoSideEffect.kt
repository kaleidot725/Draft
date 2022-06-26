package jp.kaleidot725.draft.view.pages.memo.delete

sealed class DeleteMemoSideEffect {
    object BackHome : DeleteMemoSideEffect()
    object Close : DeleteMemoSideEffect()
}
