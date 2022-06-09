package jp.kaleidot725.emomemo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert
    suspend fun insert(memoEntity: MemoEntity): Long

    @Update
    suspend fun update(memoEntity: MemoEntity)

    @Delete
    suspend fun delete(memoEntity: MemoEntity)

    @Query("delete from memo where id in (:memoEntityIds)")
    suspend fun delete(memoEntityIds: List<Long>)

    @Query("select * from memo where id = :id")
    suspend fun getMemoByMemoId(id: Long): MemoEntity

    @Query("select * from memo where notebookId = :notebookId")
    suspend fun getMemosByNotebookId(notebookId: Long): List<MemoEntity>

    @Query("select * from memo where notebookId = :notebookId")
    fun getMemosFlowByNotebookId(notebookId: Long): Flow<List<MemoEntity>>

    @Query("select * from memo where memo.id = :memoId")
    fun getMemosFlowByMemoId(memoId: Long): Flow<List<MemoEntity>>
}
