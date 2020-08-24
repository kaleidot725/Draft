package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoDao
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity

class MemoRepository(private val dao: MemoDao) {
    suspend fun insert(memo: MemoEntity) {
        dao.insert(memo)
    }

    suspend fun update(memo: MemoEntity) {
        dao.update(memo)
    }

    suspend fun delete(memoEntity: MemoEntity) {
        dao.delete(memoEntity)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun getAll(): List<MemoEntity> {
        return dao.getAll()
    }

    suspend fun getMemo(id: Int): MemoEntity {
        return dao.getMemo(id)
    }
}
