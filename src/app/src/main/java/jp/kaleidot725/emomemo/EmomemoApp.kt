package jp.kaleidot725.emomemo

import android.app.Application
import jp.kaleidot725.emomemo.data.dataModule
import jp.kaleidot725.emomemo.domain.usecase.domainModule
import jp.kaleidot725.emomemo.view.pages.memo.add.AddMemoViewModel
import jp.kaleidot725.emomemo.view.pages.memo.delete.DeleteMemoViewModel
import jp.kaleidot725.emomemo.view.pages.memo.detail.MemoDetailViewModel
import jp.kaleidot725.emomemo.view.pages.notebook.add.AddNotebookViewModel
import jp.kaleidot725.emomemo.view.pages.notebook.delete.DeleteNotebookViewModel
import jp.kaleidot725.emomemo.view.pages.notebook.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import timber.log.Timber

class EmomemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDI()
    }

    private fun setupDI() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(applicationContext)
            modules(appModule, dataModule, domainModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

val appModule = module {
    viewModel {
        MainViewModel(get(), get(), get())
    }

    viewModel {
        AddNotebookViewModel(get())
    }

    viewModel { (notebookId: Long) ->
        DeleteNotebookViewModel(notebookId, get(), get())
    }

    viewModel { (notebookId: Long) ->
        AddMemoViewModel(notebookId, get())
    }

    viewModel { (memoId: Long) ->
        MemoDetailViewModel(memoId, get(), get())
    }

    viewModel { (memoId: Long) ->
        DeleteMemoViewModel(memoId, get(), get())
    }
}
