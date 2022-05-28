package jp.kaleidot725.emomemo.view.pages.memo.delete

sealed class DeleteMemoSideEffect {
    object BackHome : DeleteMemoSideEffect()
    object Close : DeleteMemoSideEffect()
}
