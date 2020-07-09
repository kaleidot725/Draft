package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.MemoStatusDao
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusRepository(private val dao: MemoStatusDao) {
    suspend fun getAll(): List<MemoStatusView> {
        return dao.getAll()
    }

    suspend fun getMemo(id: Int): MemoStatusView {
        return dao.getMemo(id)
    }
}
