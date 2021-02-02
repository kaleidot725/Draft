package jp.kaleidot725.emomemo

import androidx.room.Room
import jp.kaleidot725.emomemo.model.db.AppDatabase
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.ApplicationRepository
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository
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
import jp.kaleidot725.emomemo.usecase.CreateMemoUseCase
import jp.kaleidot725.emomemo.usecase.CreateMessageUseCase
import jp.kaleidot725.emomemo.usecase.CreateNotebookUseCase
import jp.kaleidot725.emomemo.usecase.DeleteMemoUseCase
import jp.kaleidot725.emomemo.usecase.DeleteMessagesUseCase
import jp.kaleidot725.emomemo.usecase.DeleteNotebookUseCase
import jp.kaleidot725.emomemo.usecase.GetInitializedUseCase
import jp.kaleidot725.emomemo.usecase.GetMemoCountUseCase
import jp.kaleidot725.emomemo.usecase.GetMemoUseCase
import jp.kaleidot725.emomemo.usecase.GetMemosUseCase
import jp.kaleidot725.emomemo.usecase.GetMessageCountUseCase
import jp.kaleidot725.emomemo.usecase.GetMessageUseCase
import jp.kaleidot725.emomemo.usecase.GetNotebookUseCase
import jp.kaleidot725.emomemo.usecase.GetNotebooksUseCase
import jp.kaleidot725.emomemo.usecase.GetStatusUseCase
import jp.kaleidot725.emomemo.usecase.ObserveMemoCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveNotebookCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveRecognizedTextUseCase
import jp.kaleidot725.emomemo.usecase.UpdateInitializedUseCase
import jp.kaleidot725.emomemo.usecase.UpdateMessageUseCase
import jp.kaleidot725.emomemo.usecase.UpdateNotebookUseCase
import jp.kaleidot725.emomemo.usecase.select.DeleteSelectedMemoUseCase
import jp.kaleidot725.emomemo.usecase.select.DeleteSelectedMessageUseCase
import jp.kaleidot725.emomemo.usecase.select.GetSelectedMemoUseCase
import jp.kaleidot725.emomemo.usecase.select.GetSelectedMessageUseCase
import jp.kaleidot725.emomemo.usecase.select.ReselectMemoUseCase
import jp.kaleidot725.emomemo.usecase.select.ReselectNotebookUseCase
import jp.kaleidot725.emomemo.usecase.select.SelectMemoUseCase
import jp.kaleidot725.emomemo.usecase.select.SelectMessageUseCase
import jp.kaleidot725.emomemo.usecase.select.SelectNotebookUseCase
import jp.kaleidot725.emomemo.usecase.select.UpdateSelectedMemoUseCase
import jp.kaleidot725.emomemo.usecase.select.UpdateSelectedMessageUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
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

    single {
        ApplicationRepository()
    }

    factory {
        ObserveRecognizedTextUseCase(get())
    }

    factory {
        CreateMemoUseCase(get(), get())
    }

    factory {
        CreateMessageUseCase(get(), get())
    }

    factory {
        GetMessageUseCase(get())
    }

    factory {
        GetMemoUseCase(get())
    }

    factory {
        GetMemoCountUseCase(get())
    }

    factory {
        SelectMemoUseCase(get())
    }

    factory {
        CreateNotebookUseCase(get(), get())
    }

    factory {
        DeleteNotebookUseCase(get(), get())
    }

    factory {
        GetNotebookUseCase(get())
    }

    factory {
        SelectNotebookUseCase(get(), get())
    }

    factory {
        DeleteMemoUseCase(get())
    }

    factory {
        DeleteMessagesUseCase(get())
    }

    factory {
        UpdateMessageUseCase(get())
    }

    factory {
        UpdateNotebookUseCase(get())
    }

    factory {
        GetStatusUseCase(get())
    }

    factory {
        GetNotebooksUseCase(get())
    }

    factory {
        GetMemosUseCase(get())
    }

    factory {
        GetMessageCountUseCase(get())
    }

    factory {
        ObserveMemoCountUseCase(get())
    }

    factory {
        ObserveNotebookCountUseCase(get())
    }

    factory {
        ReselectNotebookUseCase(get(), get())
    }

    factory {
        ReselectMemoUseCase(get(), get())
    }

    factory {
        DeleteSelectedMemoUseCase(get(), get())
    }

    factory {
        GetSelectedMemoUseCase(get(), get())
    }

    factory {
        UpdateSelectedMemoUseCase(get(), get())
    }

    factory {
        DeleteSelectedMessageUseCase(get(), get())
    }

    factory {
        GetSelectedMessageUseCase(get(), get())
    }

    factory {
        SelectMessageUseCase(get())
    }

    factory {
        UpdateSelectedMessageUseCase(get(), get())
    }

    factory {
        GetInitializedUseCase(get())
    }

    factory {
        UpdateInitializedUseCase(get())
    }

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

    viewModel { (notebook: NotebookEntity) ->
        EditNotebookDialogViewModel(notebook, get())
    }

    viewModel {
        MemoOptionDialogViewModel(get(), get())
    }

    viewModel {
        MessageOptionDialogViewModel(get(), get())
    }
}
