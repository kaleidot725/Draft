package jp.kaleidot725.emomemo.view.pages.main

sealed class MainSideEffect {
    object NavigateAddNotebook : MainSideEffect()
    object NavigateRemoveNotebook : MainSideEffect()
    object NavigateMemoDetails : MainSideEffect()
    object NavigateAddMemo : MainSideEffect()
}