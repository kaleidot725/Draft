package jp.kaleidot725.emomemo.domain.usecase

import jp.kaleidot725.emomemo.domain.usecase.create.CreateMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.create.CreateNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.delete.DeleteMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.delete.DeleteNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetFilteredMemoFlow
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemosFlowUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMemosUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebookUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetNotebooksFlowUseCase
import jp.kaleidot725.emomemo.domain.usecase.update.UpdateMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.update.UpdateNotebookUseCase
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
        GetMemosFlowUseCase(get())
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
        GetNotebooksFlowUseCase(get())
    }
    factory {
        GetMemosUseCase(get())
    }
    factory {
        GetFilteredMemoFlow(get())
    }
}
