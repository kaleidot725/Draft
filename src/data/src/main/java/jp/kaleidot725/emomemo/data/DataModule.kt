package jp.kaleidot725.emomemo.data

import androidx.room.Room
import jp.kaleidot725.emomemo.data.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.data.repository.MemoRepository
import jp.kaleidot725.emomemo.data.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.data.repository.MessageRepository
import jp.kaleidot725.emomemo.data.repository.NotebookRepository
import jp.kaleidot725.emomemo.data.repository.StatusRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    // 変更通知の仕組みを実現するためにシングルトンで定義している
    single {
        AudioRecognizerRepository()
    }

    factory {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "emomemo-database").build()
    }

    factory {
        val db: AppDatabase = get()
        NotebookRepository(db.notebookDao())
    }

    factory {
        val db: AppDatabase = get()
        MemoRepository(db.memoDao())
    }

    factory {
        val db: AppDatabase = get()
        MessageRepository(db.messageDao())
    }

    factory {
        val db: AppDatabase = get()
        MemoStatusRepository(db.memoStatusDao())
    }

    factory {
        val db: AppDatabase = get()
        StatusRepository(db.statusDao())
    }
}
