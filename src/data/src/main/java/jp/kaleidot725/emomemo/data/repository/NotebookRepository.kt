package jp.kaleidot725.emomemo.data.repository

import jp.kaleidot725.emomemo.data.dao.NotebookDao
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import kotlinx.coroutines.flow.Flow

class NotebookRepository(private val dao: NotebookDao) {
    suspend fun insert(notebook: NotebookEntity): Long {
        return dao.insert(notebook)
    }

    suspend fun update(notebook: NotebookEntity) {
        dao.update(notebook)
    }

    suspend fun delete(notebook: NotebookEntity) {
        dao.delete(notebook)
    }

    suspend fun delete(notebookIds: List<Long>) {
        dao.delete(notebookIds)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }

    suspend fun getAll(): List<NotebookEntity> {
        return dao.getAll()
    }

    fun getAllFlow(): Flow<List<NotebookEntity>> {
        return dao.getAllFlow()
    }

    suspend fun getNoteBook(id: Long): NotebookEntity? {
        return dao.getNotebook(id)
    }
}
