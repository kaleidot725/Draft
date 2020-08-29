package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoStatusDao
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusRepository(private val dao: MemoStatusDao) {
    suspend fun getAll(): List<MemoStatusView> {
        return dao.getAll()
    }

    suspend fun getPage(id: Int, no: Int, limit: Int): List<MemoStatusView> {
        return dao.getPage(id, (no - 1) * limit, limit)
    }

    suspend fun getMemoByMemoId(id: Int): MemoStatusView {
        return dao.getMemoByMemoId(id)
    }

    suspend fun getMemoListByNotebookId(id: Int): List<MemoStatusView> {
        return dao.getMemoListByNotebookId(id)
    }
}
