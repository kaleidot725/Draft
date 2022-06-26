package jp.kaleidot725.draft.view.pages.memo.detail

import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.texteditor.state.TextEditorState

data class MemoDetailState(
    val memoEntity: MemoEntity? = null,
    val editorState: TextEditorState? = null
)
