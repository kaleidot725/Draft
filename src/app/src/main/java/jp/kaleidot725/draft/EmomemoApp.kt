package jp.kaleidot725.draft

import android.app.Application
import jp.kaleidot725.draft.data.dataModule
import jp.kaleidot725.draft.domain.usecase.domainModule
import jp.kaleidot725.draft.view.pages.memo.add.AddMemoViewModel
import jp.kaleidot725.draft.view.pages.memo.bottom.MemoBottomSheetViewModel
import jp.kaleidot725.draft.view.pages.memo.delete.DeleteMemoViewModel
import jp.kaleidot725.draft.view.pages.memo.detail.MemoDetailViewModel
import jp.kaleidot725.draft.view.pages.memo.edit.EditMemoViewModel
import jp.kaleidot725.draft.view.pages.notebook.add.AddNotebookViewModel
import jp.kaleidot725.draft.view.pages.notebook.bottom.NotebookBottomSheetViewModel
import jp.kaleidot725.draft.view.pages.notebook.delete.DeleteNotebookViewModel
import jp.kaleidot725.draft.view.pages.notebook.edit.EditNotebookViewModel
import jp.kaleidot725.draft.view.pages.notebook.main.MainViewModel
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
        MainViewModel(get(), get())
    }

    viewModel { (notebookId: Long) ->
        NotebookBottomSheetViewModel(notebookId, get())
    }

    viewModel {
        AddNotebookViewModel(get())
    }

    viewModel { (notebookId: Long) ->
        DeleteNotebookViewModel(notebookId, get(), get())
    }

    viewModel { (notebookId: Long) ->
        EditNotebookViewModel(notebookId, get(), get())
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

    viewModel { (memoId: Long) ->
        EditMemoViewModel(memoId, get(), get())
    }

    viewModel { (memoId: Long) ->
        MemoBottomSheetViewModel(memoId, get())
    }
}
