package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoStatusDao
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusRepository(private val dao: MemoStatusDao) {
    suspend fun getAll(): List<MemoStatusView> {
        return dao.getAll()
    }

    suspend fun getMemoByMemoId(id: Int): MemoStatusView {
        return dao.getMemoByMemoId(id)
    }

    suspend fun firstByNotebookId(id: Int): MemoStatusView? {
        return dao.firstByNotebookId(id)
    }

    suspend fun getMemoListByNotebookId(id: Int): List<MemoStatusView> {
        return dao.getMemoListByNotebookId(id)
    }
}
