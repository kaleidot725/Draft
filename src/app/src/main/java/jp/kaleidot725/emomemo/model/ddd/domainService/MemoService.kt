package jp.kaleidot725.emomemo.model.domain

import jp.kaleidot725.emomemo.model.room.entity.MemoEntity
import jp.kaleidot725.emomemo.model.room.repository.MemoRepository

class MemoService(private val memoRepository: MemoRepository) {
    suspend fun create(tag: String, title: String) {
        memoRepository.insert(MemoEntity(0, tag, title))
    }
}
