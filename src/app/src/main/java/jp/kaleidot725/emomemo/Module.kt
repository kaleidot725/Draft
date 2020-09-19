package jp.kaleidot725.emomemo

import androidx.room.Room
import jp.kaleidot725.emomemo.model.db.AppDatabase
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.ui.MainViewModel
import jp.kaleidot725.emomemo.ui.audio.AudioRecordViewModel
import jp.kaleidot725.emomemo.ui.home.HomeViewModel
import jp.kaleidot725.emomemo.ui.memo.AddMemoDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.EditMemoDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.MemoViewModel
import jp.kaleidot725.emomemo.ui.message.EditMessageDialogViewModel
import jp.kaleidot725.emomemo.ui.notebook.AddNotebookViewModel
import jp.kaleidot725.emomemo.ui.notebook.DeleteNotebookViewModel
import jp.kaleidot725.emomemo.ui.top.TopViewModel
import jp.kaleidot725.emomemo.usecase.CreateMemoUseCase
import jp.kaleidot725.emomemo.usecase.CreateMessageUseCase
import jp.kaleidot725.emomemo.usecase.CreateNotebookUseCase
import jp.kaleidot725.emomemo.usecase.DeleteMemosUseCase
import jp.kaleidot725.emomemo.usecase.DeleteMessagesUseCase
import jp.kaleidot725.emomemo.usecase.DeleteNotebookUseCase
import jp.kaleidot725.emomemo.usecase.GetMemoUseCase
import jp.kaleidot725.emomemo.usecase.GetMessageUseCase
import jp.kaleidot725.emomemo.usecase.GetNotebookUseCase
import jp.kaleidot725.emomemo.usecase.InitializeDataBaseUseCase
import jp.kaleidot725.emomemo.usecase.ObserveMemoCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveMessageCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveRecognizedTextUseCase
import jp.kaleidot725.emomemo.usecase.ObserveStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectMemoUseCase
import jp.kaleidot725.emomemo.usecase.SelectNotebookUseCase
import jp.kaleidot725.emomemo.usecase.UpdateMemoUseCase
import jp.kaleidot725.emomemo.usecase.UpdateMessageUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
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
        val db: AppDatabase = get()
        StatusRepository(db.statusDao())
    }

    single {
        AudioRecognizerRepository()
    }

    single {
        InitializeDataBaseUseCase(get(), get())
    }

    single {
        ObserveMemoCountUseCase(get())
    }

    single {
        ObserveMessageCountUseCase(get())
    }

    single {
        ObserveRecognizedTextUseCase(get())
    }

    single {
        ObserveStatusUseCase(get())
    }

    single {
        CreateMemoUseCase(get(), get())
    }

    single {
        CreateMessageUseCase(get(), get())
    }

    single {
        GetMessageUseCase(get())
    }

    single {
        GetMemoUseCase(get())
    }

    single {
        SelectMemoUseCase(get())
    }

    single {
        CreateNotebookUseCase(get(), get())
    }

    single {
        DeleteNotebookUseCase(get())
    }

    single {
        GetNotebookUseCase(get())
    }

    single {
        SelectNotebookUseCase(get(), get())
    }

    single {
        DeleteMemosUseCase(get())
    }

    single {
        DeleteMessagesUseCase(get())
    }

    single {
        UpdateMemoUseCase(get())
    }

    single {
        UpdateMessageUseCase(get())
    }
    
    viewModel {
        HomeViewModel(get(), get(), get(), get(), get())
    }

    viewModel {
        TopViewModel(get())
    }

    viewModel {
        AddNotebookViewModel(get())
    }

    viewModel {
        DeleteNotebookViewModel(get(), get())
    }

    viewModel {
        AddMemoDialogViewModel(get())
    }

    viewModel {
        MemoViewModel(get(), get(), get(), get(), get(), get())
    }

    viewModel {
        AudioRecordViewModel(get())
    }

    viewModel {
        MainViewModel(get(), get(), get())
    }

    viewModel { (memo: MemoStatusView) ->
        EditMemoDialogViewModel(memo, get())
    }

    viewModel { (message: MessageEntity) ->
        EditMessageDialogViewModel(message, get())
    }
}
