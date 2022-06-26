package jp.kaleidot725.draft.view.pages.notebook.edit

import jp.kaleidot725.draft.data.entity.NotebookEntity

data class EditNotebookState(
    val notebook: NotebookEntity? = null,
    val notebookTitle: String = ""
) {
    val canRename: Boolean = notebookTitle.isNotBlank()
}