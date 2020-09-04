package jp.kaleidot725.emomemo.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setOnLongClickListener")
fun View.setOnLongClickListener(listener: View.OnLongClickListener) {
    this.setOnLongClickListener(listener)
}
