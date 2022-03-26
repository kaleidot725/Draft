package jp.kaleidot725.emomemo.view.pages.notebook

sealed class DeleteNotebookSideEffect {
    object BackHome : DeleteNotebookSideEffect()
    object Close : DeleteNotebookSideEffect()
}