package jp.kaleidot725.emomemo.model

data class AppStatus(
    var memoId: Int = UNKNOWN_MEMO_ID,
    var notebookId: Int = UNKNOWN_NOTEBOOK_ID
) {
    companion object {
        val UNKNOWN_MEMO_ID = -1
        val UNKNOWN_NOTEBOOK_ID = -1
    }
}
