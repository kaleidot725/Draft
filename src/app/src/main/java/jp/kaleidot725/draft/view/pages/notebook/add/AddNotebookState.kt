package jp.kaleidot725.draft.view.pages.notebook.add

data class AddNotebookState(
    val notebookTitle: String = "",
) {
    val canCreate: Boolean = notebookTitle.isNotBlank()
}
