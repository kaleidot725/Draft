package jp.kaleidot725.emomemo.model.domain

data class Message(
    val memoId: Int,
    val time: Long,
    val value: String
)
