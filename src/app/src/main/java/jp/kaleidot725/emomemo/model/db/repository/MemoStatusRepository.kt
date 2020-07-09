package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoStatusDao
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusRepository(private val dao: MemoStatusDao) {
    fun getAll(): List<MemoStatusView> {
        return dao.getAll()
    }

    fun getMemo(id: Int): MemoStatusView {
        return dao.getMemo(id)
    }
}
