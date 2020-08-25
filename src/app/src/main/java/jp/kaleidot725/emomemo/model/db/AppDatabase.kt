package jp.kaleidot725.emomemo.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.emomemo.model.db.dao.MemoDao
import jp.kaleidot725.emomemo.model.db.dao.MemoStatusDao
import jp.kaleidot725.emomemo.model.db.dao.MessageDao
import jp.kaleidot725.emomemo.model.db.dao.NotebookDao
import jp.kaleidot725.emomemo.model.db.dao.StatusDao
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

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
