package jp.kaleidot725.emomemo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import kotlinx.coroutines.flow.Flow

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
    fun getAllFlow(): Flow<List<NotebookEntity>>

    @Query("select * from notebook where id = :id")
    suspend fun getNotebook(id: Int): NotebookEntity?
}
