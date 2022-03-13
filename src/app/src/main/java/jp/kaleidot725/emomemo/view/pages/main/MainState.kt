package jp.kaleidot725.emomemo.view.pages.main

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity

data class MainState(
    val notebooks: List<NotebookEntity> = emptyList(),
    val selectedNotebook: NotebookEntity? = null,
    val memos: List<MemoEntity> = emptyList()
)