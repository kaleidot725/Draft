package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.data.view.MemoStatusView
import jp.kaleidot725.emomemo.domain.usecase.select.DeleteSelectedMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.GetSelectedMemoUseCase
import kotlinx.coroutines.launch

class MemoOptionDialogViewModel(
    private val getSelectedMemoUseCase: GetSelectedMemoUseCase,
    private val deleteSelectedMemoUseCase: DeleteSelectedMemoUseCase
) : ViewModel() {
    private val _memo: MutableLiveData<MemoStatusView> = MutableLiveData()
    val memo: LiveData<MemoStatusView> = _memo

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    init {
        viewModelScope.launch {
            _memo.value = getSelectedMemoUseCase.execute()
        }
    }

    fun edit() {
        viewModelScope.launch {
            _navEvent.value = NavEvent.NavigateMemoEdit
        }
    }

    fun delete() {
        viewModelScope.launch {
            deleteSelectedMemoUseCase.execute()
            _navEvent.value = NavEvent.NavigateMemoDelete
        }
    }

    sealed class NavEvent {
        object NavigateMemoEdit : NavEvent()
        object NavigateMemoDelete : NavEvent()
    }
}