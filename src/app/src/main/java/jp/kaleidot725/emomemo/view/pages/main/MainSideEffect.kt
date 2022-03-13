package jp.kaleidot725.emomemo.view.pages.main

import jp.kaleidot725.emomemo.data.entity.MemoEntity

sealed class MainSideEffect {
    object NavigateAddNotebook : MainSideEffect()
    object NavigateRemoveNotebook : MainSideEffect()
    data class NavigateMemoDetails(val memoEntity: MemoEntity) : MainSideEffect()
}