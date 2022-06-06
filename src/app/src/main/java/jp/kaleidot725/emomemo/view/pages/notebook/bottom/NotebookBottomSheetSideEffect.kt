package jp.kaleidot725.emomemo.view.pages.notebook.bottom

sealed class NotebookBottomSheetSideEffect {
    data class NavigateDeleteNotebook(val notebookId: Long) : NotebookBottomSheetSideEffect()
    data class NavigateEditNotebook(val notebookId: Long) : NotebookBottomSheetSideEffect()
}
