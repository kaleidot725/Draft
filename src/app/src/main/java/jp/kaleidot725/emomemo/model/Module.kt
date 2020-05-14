package jp.kaleidot725.emomemo.model

import jp.kaleidot725.emomemo.ui.home.HomeViewModel
import jp.kaleidot725.emomemo.ui.homedialog.HomeDialogViewModel
import jp.kaleidot725.emomemo.ui.memo.MemoViewModel
import jp.kaleidot725.emomemo.ui.top.TopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
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