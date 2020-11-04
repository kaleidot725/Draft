package jp.kaleidot725.emomemo.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> LayoutInflater.inflateDB(
    container: ViewGroup?,
    layout: Int,
    attachToParent: Boolean
): T {
    return DataBindingUtil.inflate<T>(this, layout, container, attachToParent)
}

@BindingAdapter("app:setOnLongClickListener")
fun View.setOnLongClickListener(listener: View.OnLongClickListener) {
    this.setOnLongClickListener(listener)
}

@BindingAdapter("app:textResId")
fun TextView.setTextResId(resId: Int?) {
    if (resId != null) {
        this.text = context.getString(resId)
    }
}