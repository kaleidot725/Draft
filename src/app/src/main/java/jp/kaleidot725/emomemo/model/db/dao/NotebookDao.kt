package jp.kaleidot725.emomemo.model.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity

@Dao
interface NotebookDao {
    @Insert
    fun insert(memoEntity: NotebookEntity)

    @Update
    fun update(memoEntity: NotebookEntity)

    @Delete
    fun delete(memoEntity: NotebookEntity)

    @Query("delete from notebook")
    fun deleteAll()

    @Query("select * from notebook ORDER BY notebook.id DESC LIMIT 1")
    fun first(): NotebookEntity?

    @Query("select * from notebook")
    fun getAll(): List<NotebookEntity>

    @Query("select * from notebook where id = :id")
    fun getNotebook(id: Int): NotebookEntity
}
