package jp.kaleidot725.emomemo.model.db.repository

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.dao.StatusDao
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity

class StatusRepository(private val dao: StatusDao) {
    suspend fun update(notebookId: Int, memoID: Int, messageId: Int) {
        dao.insert(StatusEntity(DEFAULT_ID, notebookId, memoID, messageId))
    }

    suspend fun get(): StatusEntity? {
        return dao.get(DEFAULT_ID)
    }

    fun getLiveData(): LiveData<StatusEntity?> {
        return dao.getLiveData(DEFAULT_ID)
    }

    companion object {
        private const val DEFAULT_ID = 0
    }
}
