package jp.kaleidot725.emomemo.domain.usecase

import jp.kaleidot725.emomemo.domain.usecase.create.CreateMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.create.CreateMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.create.CreateNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.delete.DeleteMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.delete.DeleteMessagesUseCase
import jp.kaleidot725.emomemo.domain.usecase.delete.DeleteNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemoCountUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemosUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMessageCountUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebooksUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetStatusUseCase
import jp.kaleidot725.emomemo.domain.usecase.observe.ObserveMemoCountUseCase
import jp.kaleidot725.emomemo.domain.usecase.observe.ObserveNotebookCountUseCase
import jp.kaleidot725.emomemo.domain.usecase.observe.ObserveRecognizedTextUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.DeleteSelectedMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.DeleteSelectedMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.GetSelectedMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.GetSelectedMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.ReselectMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.ReselectNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.SelectMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.SelectMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.SelectNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.UpdateSelectedMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.UpdateSelectedMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.update.UpdateMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.update.UpdateNotebookUseCase
import org.koin.dsl.module

val domainModule = module {
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
}
