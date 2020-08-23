package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoStatusDao
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusRepository(private val dao: MemoStatusDao) {
    fun getAll(): List<MemoStatusView> {
        return dao.getAll()
    }

    fun getPage(no: Int, limit: Int): List<MemoStatusView> {
        return dao.getPage(no, limit)
    }
   
    fun getMemoByMemoId(id: Int): MemoStatusView {
        return dao.getMemoByMemoId(id)
    }

    fun firstByNotebookId(id: Int): MemoStatusView? {
        return dao.firstByNotebookId(id)
    }

    fun getMemoListByNotebookId(id: Int): List<MemoStatusView> {
        return dao.getMemoListByNotebookId(id)
    }
}
