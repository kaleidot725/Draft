package jp.kaleidot725.emomemo.view.pages.main

sealed class MainSideEffect {
    object NavigateAddNotebook : MainSideEffect()
    data class NavigateDeleteNotebook(val notebookId: Long) : MainSideEffect()
    data class NavigateMemoDetails(val newMemoId: Long) : MainSideEffect()
}
