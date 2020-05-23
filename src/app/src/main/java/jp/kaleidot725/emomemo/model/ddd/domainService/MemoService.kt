package jp.kaleidot725.emomemo.model.ddd.domainService

import jp.kaleidot725.emomemo.extension.toMemo
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.ddd.domain.Memo

class MemoService(private val repository: MemoRepository) {
    suspend fun create(tag: String, title: String) {
        repository.insert(MemoEntity(0, tag, title))
    }

    suspend fun getAll(): List<Memo> {
        return repository.getAll().map { it.toMemo() }
    }

    suspend fun getMemo(id: Int): Memo {
        return repository.getMemo(id).toMemo()
    }
}
