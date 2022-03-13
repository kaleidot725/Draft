package jp.kaleidot725.emomemo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.emomemo.data.dao.MemoDao
import jp.kaleidot725.emomemo.data.dao.NotebookDao
import jp.kaleidot725.emomemo.data.entity.MemoEntity
import jp.kaleidot725.emomemo.data.entity.NotebookEntity

@Database(
    entities = [NotebookEntity::class, MemoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notebookDao(): NotebookDao
    abstract fun memoDao(): MemoDao
}
