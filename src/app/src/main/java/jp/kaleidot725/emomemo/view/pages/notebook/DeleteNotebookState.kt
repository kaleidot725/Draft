package jp.kaleidot725.emomemo.view.pages.notebook

import jp.kaleidot725.emomemo.data.entity.NotebookEntity

data class DeleteNotebookState(
    val notebooks: List<NotebookEntity> = emptyList(),
    val selectedNotebook: NotebookEntity? = null
) {
    val canDelete = selectedNotebook != null
}
