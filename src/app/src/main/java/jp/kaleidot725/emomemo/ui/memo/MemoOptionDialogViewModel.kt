package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.DeleteMemoUseCase
import jp.kaleidot725.emomemo.usecase.GetMemoUseCase
import jp.kaleidot725.emomemo.usecase.GetStatusUseCase
import kotlinx.coroutines.launch

class MemoOptionDialogViewModel(
    private val getStatusUseCase: GetStatusUseCase,
    private val getMemoUseCase: GetMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase,
) : ViewModel() {
    private val _memo: MutableLiveData<MemoStatusView> = MutableLiveData()
    val memo: LiveData<MemoStatusView> = _memo

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    init {
        viewModelScope.launch {
            val status = getStatusUseCase.execute()
            val selected = getMemoUseCase.execute(status.memoId) ?: return@launch
            _memo.value = selected
        }
    }

    fun edit() {
        viewModelScope.launch {
            _navEvent.value = NavEvent.NavigateMemoEdit
        }
    }

    fun delete() {
        viewModelScope.launch {
            val status = getStatusUseCase.execute()
            deleteMemoUseCase.execute(status.memoId)
            _navEvent.value = NavEvent.NavigateMemoDelete
        }
    }

    sealed class NavEvent {
        object NavigateMemoEdit : NavEvent()
        object NavigateMemoDelete : NavEvent()
    }
}