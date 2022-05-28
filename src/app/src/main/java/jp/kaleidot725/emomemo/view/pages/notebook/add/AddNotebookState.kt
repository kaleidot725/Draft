package jp.kaleidot725.emomemo.view.pages.notebook.add

data class AddNotebookState(
    val notebookTitle: String = "",
) {
    val canCreate: Boolean = notebookTitle.isNotBlank()
}
