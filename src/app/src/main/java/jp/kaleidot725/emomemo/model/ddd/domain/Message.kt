package jp.kaleidot725.emomemo.model.ddd.domain

import android.text.format.DateFormat

data class Message(
    val memoId: Int,
    val time: Long,
    val value: String
) {
    val formattedTime = DateFormat.format("yyyy/MM/dd hh:mm:ss", time).toString()
}
