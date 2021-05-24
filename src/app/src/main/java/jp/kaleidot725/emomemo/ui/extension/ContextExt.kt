package jp.kaleidot725.emomemo.ui.extension

import android.content.Context
import androidx.annotation.StringRes

fun Context?.getSafetyString(@StringRes resId: Int): String {
    return this?.resources?.getString(resId) ?: ""
}
