package jp.kaleidot725.emomemo.model.db.repository

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.dao.NotebookDao
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity

class NotebookRepository(private val dao: NotebookDao) {
    suspend fun insert(notebook: NotebookEntity) {
        dao.insert(notebook)
    }

    suspend fun update(notebook: NotebookEntity) {
        dao.update(notebook)
    }

    suspend fun delete(notebook: NotebookEntity) {
        dao.delete(notebook)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun getAll(): List<NotebookEntity> {
        return dao.getAll()
    }

    fun getAllLiveData(): LiveData<List<NotebookEntity>> {
        return dao.getAllLiveData()
    }

    suspend fun getNoteBook(id: Int): NotebookEntity? {
        return dao.getNotebook(id)
    }
}
