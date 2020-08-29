package jp.kaleidot725.emomemo.model.db.repository

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.dao.MemoDao
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity

class MemoRepository(private val dao: MemoDao) {
    suspend fun insert(memo: MemoEntity) {
        dao.insert(memo)
    }

    suspend fun delete(memoEntity: MemoEntity) {
        dao.delete(memoEntity)
    }

    suspend fun getMemo(id: Int): MemoEntity {
        return dao.getMemo(id)
    }

    fun getMemoCount(notebookId: Int): LiveData<Int> {
        return dao.getMemoCount(notebookId)
    }
}
