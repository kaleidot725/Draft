package jp.kaleidot725.emomemo.data.repository

import jp.kaleidot725.emomemo.data.dao.MemoDao
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import kotlinx.coroutines.flow.Flow

class MemoRepository(private val dao: MemoDao) {
    suspend fun insert(memo: MemoEntity): Long {
        return dao.insert(memo)
    }

    suspend fun update(memoEntity: MemoEntity) {
        dao.update(memoEntity)
    }

    suspend fun delete(memoEntity: MemoEntity) {
        dao.delete(memoEntity)
    }

    suspend fun delete(memoIds: List<Long>) {
        dao.delete(memoIds)
    }

    suspend fun getMemo(id: Long): MemoEntity {
        return dao.getMemo(id)
    }

    suspend fun getMemos(notebookId: Long): List<MemoEntity> {
        return dao.getMemos(notebookId)
    }

    suspend fun getMemosFlow(notebookId: Long): Flow<List<MemoEntity>> {
        return dao.getMemosFlow(notebookId)
    }
}
