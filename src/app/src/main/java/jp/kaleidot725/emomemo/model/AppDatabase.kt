package jp.kaleidot725.emomemo.model

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.emomemo.model.db.dao.MemoDao
import jp.kaleidot725.emomemo.model.db.dao.MessageDao
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

@Database(entities = [MemoEntity::class, MessageEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
    abstract fun messageDao(): MessageDao
}
