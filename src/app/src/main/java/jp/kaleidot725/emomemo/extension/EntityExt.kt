package jp.kaleidot725.emomemo.extension

import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.ddd.domain.Memo
import jp.kaleidot725.emomemo.model.ddd.domain.Message

fun MemoEntity.toMemo(): Memo {
    return Memo(this.id, this.tag, this.title)
}

fun Memo.toMemoEntity(): MemoEntity {
    return MemoEntity(this.id, this.tag, this.title)
}

fun MessageEntity.toMessage(): Message {
    return Message(this.memoId, this.time, this.value)
}

fun Message.toMessageEntity(): MessageEntity {
    return MessageEntity(this.memoId, this.time, this.value)
}
