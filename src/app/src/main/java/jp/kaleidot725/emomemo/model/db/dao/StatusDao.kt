package jp.kaleidot725.emomemo.model.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity

@Dao
interface StatusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(statusEntity: StatusEntity)

    @Query("select * from status where id = :id")
    suspend fun get(id: Int): StatusEntity?

    @Query("select * from status where id = :id")
    fun getLiveData(id: Int): LiveData<StatusEntity>
}
