package jp.kaleidot725.emomemo.view.pages.notebook.main

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity

data class MainState(
    val initialized: Boolean = false,
    val notebooks: List<NotebookEntity> = emptyList(),
    val selectedNotebook: NotebookEntity? = null,
    val memos: List<MemoEntity> = emptyList()
) {
    val result = when {
        !initialized -> Result.UNKNOWN
        notebooks.isEmpty() -> Result.NOT_FOUND_NOTEBOOK
        memos.isEmpty() -> Result.NOT_FOUND_MEMO
        else -> Result.SUCCESS
    }

    enum class Result {
        UNKNOWN,
        NOT_FOUND_NOTEBOOK,
        NOT_FOUND_MEMO,
        SUCCESS
    }
}
