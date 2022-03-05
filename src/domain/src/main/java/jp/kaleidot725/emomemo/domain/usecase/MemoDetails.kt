package jp.kaleidot725.emomemo.domain.usecase

import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.MessageEntity

data class MemoDetails(
    val memo: MemoEntity, val lastMessage: MessageEntity, val messageCount: Int
)