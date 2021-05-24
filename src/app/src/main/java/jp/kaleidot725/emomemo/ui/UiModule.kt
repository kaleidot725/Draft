package jp.kaleidot725.emomemo

import jp.kaleidot725.emomemo.ui.MainViewModel
import jp.kaleidot725.emomemo.ui.audio.AudioRecordViewModel
import jp.kaleidot725.emomemo.ui.home.HomeViewModel
import jp.kaleidot725.emomemo.ui.memo.AddMemoDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.EditMemoDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.MemoOptionDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.MemoViewModel
import jp.kaleidot725.emomemo.ui.message.EditMessageDialogViewModel
import jp.kaleidot725.emomemo.ui.message.MessageOptionDialogViewModel
import jp.kaleidot725.emomemo.ui.notebook.AddNotebookViewModel
import jp.kaleidot725.emomemo.ui.notebook.DeleteNotebookViewModel
import jp.kaleidot725.emomemo.ui.notebook.EditNotebookDialogViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        HomeViewModel(get(), get(), get(), get(), get(), get())
    }

    viewModel {
        AddNotebookViewModel(get(), get())
    }

    viewModel {
        DeleteNotebookViewModel(get(), get())
    }

    viewModel {
        AddMemoDialogViewModel(get(), get())
    }

    viewModel {
        MemoViewModel(get(), get(), get(), get(), get(), get())
    }

    viewModel {
        AudioRecordViewModel(get())
    }

    viewModel {
        MainViewModel(get(), get(), get(), get(), get())
    }

    viewModel {
        EditMemoDialogViewModel(get(), get())
    }

    viewModel {
        EditMessageDialogViewModel(get(), get())
    }

    viewModel { (notebook: jp.kaleidot725.emomemo.data.entity.NotebookEntity) ->
        EditNotebookDialogViewModel(notebook, get())
    }

    viewModel {
        MemoOptionDialogViewModel(get(), get())
    }

    viewModel {
        MessageOptionDialogViewModel(get(), get())
    }
}
