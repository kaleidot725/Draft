package jp.kaleidot725.emomemo.model.db.dao

import androidx.room.Dao
import androidx.room.Query
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

@Dao
interface MemoStatusDao {

    @Query("select * from memo_status")
    suspend fun getAll(): List<MemoStatusView>

    @Query("select * from memo_status where id = :id")
    suspend fun getMemoByMemoId(id: Int): MemoStatusView

    @Query("select * from memo_status where notebookId = :notebookId order by id DESC")
    suspend fun getMemoListByNotebookId(notebookId: Int): List<MemoStatusView>

    @Query("select * from memo_status where notebookId = :notebookId LIMIT :limit OFFSET :offset")
    suspend fun getPage(notebookId: Int, offset: Int, limit: Int): List<MemoStatusView>
}
