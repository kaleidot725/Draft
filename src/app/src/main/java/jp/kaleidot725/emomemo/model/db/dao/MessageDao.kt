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
    suspend fun insert(messageEntity: MessageEntity)

    @Update
    suspend fun update(messageEntity: MessageEntity)

    @Delete
    suspend fun delete(messageEntity: MessageEntity)

    @Query("delete from message")
    suspend fun deleteAll()

    @Query("select * from message")
    suspend fun getAll(): List<MessageEntity>

    @Query("select * from message where memoId = :id LIMIT :limit OFFSET :offset")
    suspend fun getPage(id: Int, offset: Int, limit: Int): List<MessageEntity>

    @Query("select * from message where memoId = :id")
    suspend fun getMessagesByMemoId(id: Int): List<MessageEntity>
}
