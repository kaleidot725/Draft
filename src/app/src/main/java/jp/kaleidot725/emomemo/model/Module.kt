package jp.kaleidot725.emomemo.model

import androidx.room.Room
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.ui.audio.AudioRecordViewModel
import jp.kaleidot725.emomemo.ui.home.HomeViewModel
import jp.kaleidot725.emomemo.ui.homedialog.HomeDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.MemoViewModel
import jp.kaleidot725.emomemo.ui.top.TopViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java, "emomemo-database"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        val db: AppDatabase = get()
        MemoRepository(db.memoDao())
    }

    single {
        val db: AppDatabase = get()
        MessageRepository(db.messageDao())
    }

    single {
        val db: AppDatabase = get()
        MemoStatusRepository(db.memoStatusDao())
    }

    single {
        AudioRecognizerRepository()
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        HomeDialogViewModel(get())
    }

    viewModel {
        MemoViewModel(get(), get())
    }

    viewModel {
        TopViewModel()
    }

    viewModel {
        AudioRecordViewModel(get())
    }
}
