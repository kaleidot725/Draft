package jp.kaleidot725.emomemo.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity

@Dao
interface MemoDao {
    @Insert
    suspend fun insert(memoEntity: MemoEntity)

    @Delete
    suspend fun delete(memoEntity: MemoEntity)

    @Query("delete from memo where id in (:memoEntityIds)")
    suspend fun delete(memoEntityIds: List<Int>)

    @Query("select * from memo where id = :id")
    suspend fun getMemo(id: Int): MemoEntity

    @Query("select COUNT(*) from memo where notebookId = :notebookId")
    fun getMemoCount(notebookId: Int): LiveData<Int>
}
