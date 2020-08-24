package jp.kaleidot725.emomemo.model.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity

@Dao
interface MemoDao {
    @Insert
    suspend fun insert(memoEntity: MemoEntity)

    @Update
    suspend fun update(memoEntity: MemoEntity)

    @Delete
    suspend fun delete(memoEntity: MemoEntity)

    @Query("delete from memo")
    suspend fun deleteAll()

    @Query("select * from memo")
    suspend fun getAll(): List<MemoEntity>

    @Query("select * from memo where id = :id")
    suspend fun getMemo(id: Int): MemoEntity
}
