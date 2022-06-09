package jp.kaleidot725.emomemo.view.pages.memo.edit

import jp.kaleidot725.emomemo.data.entity.MemoEntity

data class EditMemoState(
    val memo: MemoEntity? = null,
    val memoTitle: String = ""
) {
    val canRename: Boolean = memoTitle.isNotBlank()
}
