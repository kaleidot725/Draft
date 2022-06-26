package jp.kaleidot725.draft.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.draft.data.dao.MemoDao
import jp.kaleidot725.draft.data.dao.NotebookDao
import jp.kaleidot725.draft.data.entity.MemoEntity
import jp.kaleidot725.draft.data.entity.NotebookEntity

@Database(
    entities = [NotebookEntity::class, MemoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notebookDao(): NotebookDao
    abstract fun memoDao(): MemoDao
}
