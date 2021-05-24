package jp.kaleidot725.emomemo.ui.extension

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.getValueSafety(default: T) = this.value ?: default
