package jp.kaleidot725.emomemo.extension

import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.setActionBarVisibility(visible: Boolean) {
    if (visible) this.supportActionBar?.show() else this.supportActionBar?.hide()
}
