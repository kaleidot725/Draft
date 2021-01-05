package jp.kaleidot725.emomemo.model.db.dao

import androidx.lifecycle.LiveData
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

    @Query("delete from message where id in (:messageIds)")
    suspend fun delete(messageIds: List<Int>)

    @Query("delete from message")
    suspend fun deleteAll()

    @Query("select * from message")
    suspend fun getAll(): List<MessageEntity>

    @Query("select * from message where memoId = :memoId ORDER BY time ASC LIMIT :limit OFFSET :offset")
    suspend fun getPageAsc(memoId: Int, offset: Int, limit: Int): List<MessageEntity>

    @Query("select * from message where memoId = :memoId ORDER BY time DESC LIMIT :limit OFFSET :offset")
    suspend fun getPageDesc(memoId: Int, offset: Int, limit: Int): List<MessageEntity>

    @Query("select COUNT(*) from message where memoId = :memoId")
    suspend fun getMessageCount(memoId: Int): Int

    @Query("select * from message where id =:messageId")
    suspend fun getByMessageId(messageId: Int): MessageEntity?

    @Query("select COUNT(*) from message where memoId = :memoId")
    fun getMessageCountLiveData(memoId: Int): LiveData<Int>
}
