package jp.kaleidot725.draft.view.pages.memo.add

data class AddMemoState(
    val memoTitle: String = "",
) {
    val canCreate: Boolean = memoTitle.isNotBlank()
}
