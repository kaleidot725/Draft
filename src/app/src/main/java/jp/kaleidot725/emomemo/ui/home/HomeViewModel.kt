package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.ui.common.ActionModeEvent
import jp.kaleidot725.emomemo.usecase.DeleteMemosUseCase
import jp.kaleidot725.emomemo.usecase.GetMemoUseCase
import jp.kaleidot725.emomemo.usecase.GetStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectMemoUseCase
import kotlinx.coroutines.launch

data class MemosWithSelectedSet(
    val memos: PagedList<MemoStatusView>,
    val selectedMemos: List<MemoStatusView>
)

class SingleSelectList<T> {
    private val set: MutableSet<T> = mutableSetOf()

    fun add(item: T) {
        set.clear()
        set.add(item)
    }

    fun clear() {
        set.clear()
    }

    fun get(): T {
        return set.first()
    }

    fun getList(): List<T> {
        return set.toList()
    }
}

class HomeViewModel(
    private val getStatusUseCase: GetStatusUseCase,
    private val getMemoUseCase: GetMemoUseCase,
    private val selectMemoUseCase: SelectMemoUseCase,
    private val deleteMemoUseCase: DeleteMemosUseCase
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // TODO 未実装
    private val _canAddNotebook: MutableLiveData<Boolean> = MutableLiveData(true)
    val canAddNotebook: LiveData<Boolean> = _canAddNotebook

    private val selectedMemos: SingleSelectList<MemoStatusView> = SingleSelectList()

    private val _actionMode: LiveEvent<ActionModeEvent> = LiveEvent<ActionModeEvent>().apply { value = ActionModeEvent.OFF }
    val actionMode: LiveData<ActionModeEvent> = _actionMode

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    private val status: MutableLiveData<StatusEntity> = MutableLiveData()
    private val memos: LiveData<PagedList<MemoStatusView>> = status.switchMap { getMemoUseCase.execute(it.notebookId) }
    val memosWithSelectedSet: LiveData<MemosWithSelectedSet> = memos.map { MemosWithSelectedSet(it, selectedMemos.getList()) }

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
