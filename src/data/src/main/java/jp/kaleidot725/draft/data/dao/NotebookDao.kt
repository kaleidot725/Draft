package jp.kaleidot725.draft.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.draft.data.entity.NotebookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotebookDao {
    @Insert
    suspend fun insert(notebookEntity: NotebookEntity): Long

    @Update
    suspend fun update(notebookEntity: NotebookEntity)

    @Delete
    suspend fun delete(notebookEntity: NotebookEntity)

    @Query("delete from notebook where id in (:notebookEntityIds)")
    suspend fun delete(notebookEntityIds: List<Long>)

    @Query("delete from notebook")
    suspend fun deleteAll()

    @Query("select * from notebook")
    suspend fun getAll(): List<NotebookEntity>

    @Query("select * from notebook")
    fun getAllFlow(): Flow<List<NotebookEntity>>

    @Query("select * from notebook where id = :id")
    suspend fun getNotebook(id: Long): NotebookEntity?
}
