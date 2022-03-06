package jp.kaleidot725.emomemo.view.pages.main

import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.domain.usecase.MemoDetails

data class MainState(
    val notebooks: List<NotebookEntity> = emptyList(),
    val selectedNotebook: NotebookEntity? = null,
    val memos: List<MemoDetails> = emptyList()
)