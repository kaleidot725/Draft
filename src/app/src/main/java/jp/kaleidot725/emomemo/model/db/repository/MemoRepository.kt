package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoDao
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity

class MemoRepository(private val dao: MemoDao) {
    fun insert(memo: MemoEntity) {
        dao.insert(memo)
    }

    fun update(memo: MemoEntity) {
        dao.update(memo)
    }

    fun delete(memoEntity: MemoEntity) {
        dao.delete(memoEntity)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    fun getAll(): List<MemoEntity> {
        return dao.getAll()
    }

    fun getMemo(id: Int): MemoEntity {
        return dao.getMemo(id)
    }
}
