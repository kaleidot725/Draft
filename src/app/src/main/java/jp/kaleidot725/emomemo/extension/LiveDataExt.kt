package jp.kaleidot725.emomemo.extension

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.getValueSafety(default: T) = this.value ?: default

