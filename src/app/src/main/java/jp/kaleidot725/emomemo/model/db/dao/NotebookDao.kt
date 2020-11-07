package jp.kaleidot725.emomemo.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity

@Dao
interface NotebookDao {
    @Insert
    suspend fun insert(memoEntity: NotebookEntity)

    @Update
    suspend fun update(memoEntity: NotebookEntity)

    @Delete
    suspend fun delete(memoEntity: NotebookEntity)

    @Query("delete from notebook")
    suspend fun deleteAll()

    @Query("select * from notebook")
    suspend fun getAll(): List<NotebookEntity>

    @Query("select * from notebook")
    fun getAllLiveData(): LiveData<List<NotebookEntity>>

    @Query("select * from notebook where id = :id")
    suspend fun getNotebook(id: Int): NotebookEntity?

    @Query("select COUNT(*) from notebook")
    suspend fun getNotebookCount(): Int

    @Query("select COUNT(*) from notebook")
    fun getNotebookCountLiveData(): LiveData<Int>
}
