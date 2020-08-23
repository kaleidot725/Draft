package jp.kaleidot725.emomemo.model.db.dao

import androidx.room.Dao
import androidx.room.Query
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

@Dao
interface MemoStatusDao {

    @Query("select * from memo_status")
    fun getAll(): List<MemoStatusView>

    @Query("select * from memo_status where id = :id")
    fun getMemoByMemoId(id: Int): MemoStatusView

    @Query("select * from memo_status where notebookId = :notebookId order by id DESC")
    fun getMemoListByNotebookId(notebookId: Int): List<MemoStatusView>

    @Query("select * from memo_status where notebookId = :notebookId LIMIT :limit OFFSET :offset")
    fun getPage(notebookId: Int, offset: Int, limit: Int): List<MemoStatusView>

    @Query("select * from memo_status where notebookId = :notebookId order by id ASC LIMIT 1")
    fun firstByNotebookId(notebookId: Int): MemoStatusView?
}
