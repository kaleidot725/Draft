package jp.kaleidot725.emomemo.model

import androidx.room.Room
import jp.kaleidot725.emomemo.ui.home.HomeViewModel
import jp.kaleidot725.emomemo.ui.homedialog.HomeDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.MemoViewModel
import jp.kaleidot725.emomemo.ui.top.TopViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(), AppDatabase::class.java, "emomemo-database"
        ).build()
    }

    viewModel {
        HomeViewModel()
    }

    viewModel {
        HomeDialogViewModel()
    }

    viewModel {
        MemoViewModel()
    }

    viewModel {
        TopViewModel()
    }
}
