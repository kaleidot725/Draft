package jp.kaleidot725.emomemo.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity

@Dao
interface StatusDao {
    @Update
    suspend fun update(statusEntity: StatusEntity)

    @Query("select * from status where id = :id")
    fun get(id: Int): LiveData<StatusEntity>
}
