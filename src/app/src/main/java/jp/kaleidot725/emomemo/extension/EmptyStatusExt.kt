package jp.kaleidot725.emomemo.extension

import androidx.annotation.StringRes
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.ui.EmptyStatus

@StringRes
fun EmptyStatus.getMemoErrorMessage(): Int {
    return when (this) {
        EmptyStatus.MESSAGE -> R.string.memo_no_message_text
        else -> R.string.empty
    }
}

@StringRes
fun EmptyStatus.getHomeErrorMessage(): Int {
    return when (this) {
        EmptyStatus.NOTEBOOK -> R.string.home_notebook_is_not_found
        EmptyStatus.MEMO -> R.string.home_memo_is_not_found
        else -> R.string.empty
    }
}
