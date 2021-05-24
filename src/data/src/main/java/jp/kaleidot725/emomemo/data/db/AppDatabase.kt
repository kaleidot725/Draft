package jp.kaleidot725.emomemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.emomemo.data.dao.MemoDao
import jp.kaleidot725.emomemo.data.dao.MemoStatusDao
import jp.kaleidot725.emomemo.data.dao.MessageDao
import jp.kaleidot725.emomemo.data.dao.NotebookDao
import jp.kaleidot725.emomemo.data.dao.StatusDao
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.data.entity.StatusEntity
import jp.kaleidot725.emomemo.data.view.MemoStatusView

@Database(
    entities = [NotebookEntity::class, MemoEntity::class, MessageEntity::class, StatusEntity::class],
    views = [MemoStatusView::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notebookDao(): NotebookDao
    abstract fun memoDao(): MemoDao
    abstract fun messageDao(): MessageDao
    abstract fun memoStatusDao(): MemoStatusDao
    abstract fun statusDao(): StatusDao
}
