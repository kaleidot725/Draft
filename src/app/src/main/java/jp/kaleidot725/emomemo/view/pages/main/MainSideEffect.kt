package jp.kaleidot725.emomemo.view.pages.main

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity

sealed class MainSideEffect {
    object NavigateAddNotebook : MainSideEffect()
    data class NavigateDeleteNotebook(val notebookEntity: NotebookEntity) : MainSideEffect()
    data class NavigateMemoDetails(val memoEntity: MemoEntity) : MainSideEffect()
}