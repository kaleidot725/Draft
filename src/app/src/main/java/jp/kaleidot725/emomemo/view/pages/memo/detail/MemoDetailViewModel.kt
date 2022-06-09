package jp.kaleidot725.emomemo.view.pages.memo.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.domain.usecase.get.GetFilteredMemoFlow
import jp.kaleidot725.emomemo.domain.usecase.update.UpdateMemoUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MemoDetailViewModel(
    private val memoId: Long,
    private val getFilteredMemoFlow: GetFilteredMemoFlow,
    private val updateMemoUseCase: UpdateMemoUseCase
) : ViewModel(),
    ContainerHost<MemoDetailState, MemoDetailSideEffect> {
    override val container: Container<MemoDetailState, MemoDetailSideEffect> = container(MemoDetailState())

    init {
        observeMemo()
    }

    fun updateContent(content: String) {
        intent {
            val newMemo = state.memoEntity?.copy(content = content)
            if (newMemo != null) {
                updateMemoUseCase.execute(newMemo)
                reduce { state.copy(memoEntity = newMemo) }
            }
        }
    }

    fun back() {
        intent {
            postSideEffect(MemoDetailSideEffect.Back)
        }
    }

    fun deleteMemo() {
        intent {
            val id = state.memoEntity?.id ?: return@intent
            postSideEffect(MemoDetailSideEffect.DeleteMemo(id))
        }
    }

    private var memoJob: Job? = null
    private fun observeMemo() {
        memoJob?.cancel()
        memoJob = viewModelScope.launch {
            getFilteredMemoFlow.execute(memoId).collectLatest {
                intent {
                    reduce { state.copy(memoEntity = it.firstOrNull()) }
                }
            }
        }
    }
}
