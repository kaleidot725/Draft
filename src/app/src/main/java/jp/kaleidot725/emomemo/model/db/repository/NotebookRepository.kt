package jp.kaleidot725.emomemo.model.db.repository

import jp.kaleidot725.emomemo.model.db.dao.NotebookDao
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity

class NotebookRepository(private val dao: NotebookDao) {
    fun insert(notebook: NotebookEntity) {
        dao.insert(notebook)
    }

    fun update(notebook: NotebookEntity) {
        dao.update(notebook)
    }

    fun delete(notebook: NotebookEntity) {
        dao.delete(notebook)
    }

    fun deleteAll() {
        dao.deleteAll()
    }

    fun first(): NotebookEntity? {
        return dao.first()
    }

    fun getAll(): List<NotebookEntity> {
        return dao.getAll()
    }

    fun getNoteBook(id: Int): NotebookEntity {
        return dao.getNotebook(id)
    }
}
