package jp.kaleidot725.emomemo.model.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

@Dao
interface MessageDao {
    @Insert
    fun insert(messageEntity: MessageEntity)

    @Update
    fun update(messageEntity: MessageEntity)

    @Delete
    fun delete(messageEntity: MessageEntity)

    @Query("delete from message")
    fun deleteAll()

    @Query("select * from message")
    fun getAll(): List<MessageEntity>

    @Query("select * from message where memoId = :id")
    fun getMessage(id: Int): List<MessageEntity>
}
