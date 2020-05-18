package jp.kaleidot725.emomemo.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.model.entity.Message

@Dao
interface MessageDao {
    @Insert
    fun insert(message: Message)

    @Update
    fun update(message: Message)

    @Delete
    fun delete(message: Message)

    @Query("delete from message")
    fun deleteAll()

    @Query("select * from message")
    fun getAll(): List<Message>

    @Query("select * from message where memoId = :id")
    fun getMessage(id: Int): List<Message>
}
