package jp.kaleidot725.emomemo.model

import androidx.room.Room
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.ui.MainViewModel
import jp.kaleidot725.emomemo.ui.audio.AudioRecordViewModel
import jp.kaleidot725.emomemo.ui.home.HomeViewModel
import jp.kaleidot725.emomemo.ui.homedialog.HomeDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.MemoViewModel
import jp.kaleidot725.emomemo.usecase.DatabaseInitializeUsecase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        AppStatus()
    }

    single<AppDatabase> {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "emomemo-database").build()
    }

    single {
        val db: AppDatabase = get()
        NotebookRepository(db.notebookDao())
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

    single {
        DatabaseInitializeUsecase(get(), get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        HomeDialogViewModel(get(), get())
    }

    viewModel {
        MemoViewModel(get())
    }

    viewModel {
        AudioRecordViewModel(get())
    }

    viewModel {
        MainViewModel(get(), get(), get(), get())
    }
}
