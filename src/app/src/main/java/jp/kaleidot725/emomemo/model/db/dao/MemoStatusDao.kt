package jp.kaleidot725.emomemo.model.db.dao

import androidx.room.Dao
import androidx.room.Query
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

@Dao
interface MemoStatusDao {
    @Query("select * from memo_status")
    fun getAll(): List<MemoStatusView>

    @Query("select * from memo_status where id = :id")
    fun getMemo(id: Int): MemoStatusView
}
