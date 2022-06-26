package jp.kaleidot725.draft.domain.usecase

import jp.kaleidot725.draft.domain.usecase.create.CreateMemoUseCase
import jp.kaleidot725.draft.domain.usecase.create.CreateNotebookUseCase
import jp.kaleidot725.draft.domain.usecase.delete.DeleteMemoUseCase
import jp.kaleidot725.draft.domain.usecase.delete.DeleteNotebookUseCase
import jp.kaleidot725.draft.domain.usecase.get.GetMemoUseCase
import jp.kaleidot725.draft.domain.usecase.get.GetMemosUseCase
import jp.kaleidot725.draft.domain.usecase.get.GetNotebookUseCase
import jp.kaleidot725.draft.domain.usecase.get.GetNotebooksUseCase
import jp.kaleidot725.draft.domain.usecase.update.UpdateMemoUseCase
import jp.kaleidot725.draft.domain.usecase.update.UpdateNotebookUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        CreateMemoUseCase(get(), get())
    }
    factory {
        GetMemoUseCase(get())
    }
    factory {
        UpdateMemoUseCase(get())
    }
    factory {
        CreateNotebookUseCase(get())
    }
    factory {
        DeleteNotebookUseCase(get(), get())
    }
    factory {
        GetNotebookUseCase(get())
    }
    factory {
        DeleteMemoUseCase(get())
    }
    factory {
        UpdateNotebookUseCase(get())
    }
    factory {
        GetNotebooksUseCase(get())
    }
    factory {
        GetMemosUseCase(get())
    }
}
