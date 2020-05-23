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
    fun insert(memoEntity: MemoEntity)

    @Update
    fun update(memoEntity: MemoEntity)

    @Delete
    fun delete(memoEntity: MemoEntity)

    @Query("delete from memo")
    fun deleteAll()

    @Query("select * from memo")
    fun getAll(): List<MemoEntity>

    @Query("select * from memo where id = :id")
    fun getMemo(id: Int): MemoEntity
}
