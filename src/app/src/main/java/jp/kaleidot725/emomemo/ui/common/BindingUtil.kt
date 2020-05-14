package jp.kaleidot725.emomemo.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> LayoutInflater.inflateDB(
    container: ViewGroup?,
    layout: Int,
    attachToParent: Boolean
): T {
    return DataBindingUtil.inflate<T>(this, layout, container, attachToParent)
}