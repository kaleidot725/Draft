package jp.kaleidot725.emomemo.model.ddd.domain

import android.text.format.DateFormat

data class Message(
    val memoId: Int,
    val time: Long,
    val value: String
) {
    val formattedTime = DateFormat.format("yyyy年 MM月 dd日", time).toString()
}
