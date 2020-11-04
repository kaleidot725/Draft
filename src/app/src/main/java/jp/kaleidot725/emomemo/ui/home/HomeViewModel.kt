package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.extension.getHomeErrorMessage
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity.Companion.UNSELECTED_MEMO
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity.Companion.UNSELECTED_NOTEBOOK
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.ui.common.ActionModeEvent
import jp.kaleidot725.emomemo.ui.common.SingleSelectList
import jp.kaleidot725.emomemo.usecase.DeleteMemosUseCase
import jp.kaleidot725.emomemo.usecase.GetMemosUseCase
import jp.kaleidot725.emomemo.usecase.GetStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectMemoUseCase
import kotlinx.coroutines.launch

data class MemosWithSelectedSet(
    val memos: PagedList<MemoStatusView>,
    val selectedMemos: List<MemoStatusView>
)

class HomeViewModel(
    private val getStatusUseCase: GetStatusUseCase,
    private val getMemosUseCase: GetMemosUseCase,
    private val selectMemoUseCase: SelectMemoUseCase,
    private val deleteMemoUseCase: DeleteMemosUseCase
) : ViewModel() {
    private val selectedMemos: SingleSelectList<MemoStatusView> = SingleSelectList()

    private val _actionMode: LiveEvent<ActionModeEvent> = LiveEvent<ActionModeEvent>().apply { value = ActionModeEvent.OFF }
    val actionMode: LiveData<ActionModeEvent> = _actionMode

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    private val status: MutableLiveData<StatusEntity> = MutableLiveData()

    private val memos: LiveData<PagedList<MemoStatusView>> = status.switchMap { getMemosUseCase.executeLiveData(it.notebookId) }
    val memosWithSelectedSet: LiveData<MemosWithSelectedSet> = memos.map { MemosWithSelectedSet(it, selectedMemos.getList()) }
    
    val error: LiveData<Int> = status.map { it.getHomeErrorMessage() }
    val hasError: LiveData<Boolean> = status.map { it.notebookId == UNSELECTED_NOTEBOOK}
    val canAddNotebook: LiveData<Boolean> = status.map { it.notebookId != UNSELECTED_NOTEBOOK }

    fun refresh() {
        viewModelScope.launch {
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.OFF
        }
    }

    fun select(memo: MemoStatusView) {
        viewModelScope.launch {
            when (actionMode.value) {
                ActionModeEvent.ON -> {
                    selectedMemos.add(memo)
                    status.value = getStatusUseCase.execute()
                }
                ActionModeEvent.OFF -> {
                    selectMemoUseCase.execute(memo.id)
                    _navEvent.value = NavEvent.NavigateMemo
                }
            }
        }
    }

    fun startAction(memo: MemoStatusView) {
        viewModelScope.launch {
            selectedMemos.add(memo)
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.ON
        }
    }

    fun deleteAction() {
        viewModelScope.launch {
            deleteMemoUseCase.execute(selectedMemos.getList())
            selectedMemos.clear()
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.OFF
        }
    }

    fun cancelAction() {
        viewModelScope.launch {
            selectedMemos.clear()
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.OFF
        }
    }

    fun editAction() {
        _navEvent.value = NavEvent.EditMemo(selectedMemos.get())
    }

    sealed class NavEvent {
        object NavigateMemo : NavEvent()
        data class EditMemo(val memo: MemoStatusView) : NavEvent()
    }
}
