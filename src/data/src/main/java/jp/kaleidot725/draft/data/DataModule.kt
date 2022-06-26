package jp.kaleidot725.draft.data

import androidx.room.Room
import jp.kaleidot725.draft.data.db.AppDatabase
import jp.kaleidot725.draft.data.repository.MemoRepository
import jp.kaleidot725.draft.data.repository.NotebookRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
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
}
