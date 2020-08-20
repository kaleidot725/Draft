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
