package jp.kaleidot725.emomemo.view.pages.memo.add

data class AddMemoState(
    val memoTitle: String = "",
) {
    val canCreate: Boolean = memoTitle.isNotBlank()
}
