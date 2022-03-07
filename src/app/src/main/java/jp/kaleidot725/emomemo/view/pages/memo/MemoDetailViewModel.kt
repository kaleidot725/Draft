package jp.kaleidot725.emomemo.view.pages.memo

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class MemoDetailViewModel() : ViewModel(), ContainerHost<MemoDetailState, MemoDetailSideEffect> {
    override val container: Container<MemoDetailState, MemoDetailSideEffect> = container(MemoDetailState())
}