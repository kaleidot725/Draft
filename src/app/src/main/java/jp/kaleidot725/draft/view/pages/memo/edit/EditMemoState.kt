package jp.kaleidot725.draft.view.pages.memo.edit

import jp.kaleidot725.draft.data.entity.MemoEntity

data class EditMemoState(
    val memo: MemoEntity? = null,
    val memoTitle: String = ""
) {
    val canRename: Boolean = memoTitle.isNotBlank()
}
