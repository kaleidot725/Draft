package jp.kaleidot725.draft.view.pages.notebook.delete

sealed class DeleteNotebookSideEffect {
    object BackHome : DeleteNotebookSideEffect()
    object Close : DeleteNotebookSideEffect()
}
