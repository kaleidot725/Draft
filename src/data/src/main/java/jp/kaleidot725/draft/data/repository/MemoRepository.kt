package jp.kaleidot725.draft.data.repository

import jp.kaleidot725.draft.data.dao.MemoDao
import jp.kaleidot725.draft.data.entity.MemoEntity
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

    suspend fun getMemoByMemoId(id: Long): MemoEntity {
        return dao.getMemoByMemoId(id)
    }

    fun getMemosFlowByMemoId(memoId: Long): Flow<List<MemoEntity>> {
        return dao.getMemosFlowByMemoId(memoId)
    }

    suspend fun getMemosByNotebookId(notebookId: Long): List<MemoEntity> {
        return dao.getMemosByNotebookId(notebookId)
    }

    fun getMemosFlowByNotebookId(notebookId: Long): Flow<List<MemoEntity>> {
        return dao.getMemosFlowByNotebookId(notebookId)
    }
}
