package jp.kaleidot725.draft.view.pages.notebook.main

sealed class MainSideEffect {
    object NavigateAddNotebook : MainSideEffect()
    data class NavigateBottomSheet(val notebookId: Long) : MainSideEffect()
    data class NavigateAddMemo(val notebookId: Long) : MainSideEffect()
    data class NavigateMemoDetails(val memoId: Long) : MainSideEffect()
}
