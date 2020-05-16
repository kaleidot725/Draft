package jp.kaleidot725.emomemo.model

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.emomemo.model.dao.MemoDao
import jp.kaleidot725.emomemo.model.dao.MessageDao
import jp.kaleidot725.emomemo.model.entity.Memo
import jp.kaleidot725.emomemo.model.entity.Message

@Database(entities = [Memo::class, Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
    abstract fun messageDao(): MessageDao
}
