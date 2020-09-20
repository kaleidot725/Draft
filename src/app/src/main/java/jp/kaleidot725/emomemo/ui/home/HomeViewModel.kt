package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
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
import jp.kaleidot725.emomemo.usecase.ObserveStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectMemoUseCase
import kotlinx.coroutines.launch

data class MemosWithSelected(
    val memos: PagedList<MemoStatusView>,
    val selected: Set<MemoStatusView>
)

class HomeViewModel(
    private val observeStatusUseCase: ObserveStatusUseCase,
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

    private val _actionMode: LiveEvent<ActionModeEvent> = LiveEvent<ActionModeEvent>().apply { value = ActionModeEvent.OFF }
    val actionMode: LiveData<ActionModeEvent> = _actionMode

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    private val selectedSet: MutableSet<MemoStatusView> = mutableSetOf()
    private val status: MutableLiveData<StatusEntity> = MutableLiveData()
    private val memos: LiveData<PagedList<MemoStatusView>> = status.switchMap { getMemoUseCase.execute(it.notebookId) }.distinctUntilChanged()
    val memosWithSelected: LiveData<MemosWithSelected> = memos.map { MemosWithSelected(it, selectedSet) }

    init {
        observeStatusUseCase.dispose()
        observeStatusUseCase.execute { status.value = it ?: StatusEntity() }
    }

    fun refresh() {
        viewModelScope.launch { status.value = getStatusUseCase.execute() }
    }

    override fun onCleared() {
        observeStatusUseCase.dispose()
    }

    fun select(memo: MemoStatusView) {
        when (actionMode.value) {
            ActionModeEvent.ON -> {
                selectedSet.add(memo)
                refresh()
            }
            ActionModeEvent.OFF -> {
                viewModelScope.launch {
                    selectMemoUseCase.execute(memo.id)
                    _navEvent.value = NavEvent.NavigateMemo
                }
            }
        }
    }

    fun startAction(memo: MemoStatusView) {
        viewModelScope.launch {
            selectedSet.clear()
            selectedSet.add(memo)

            refresh()
            notifyActionEvent(ActionModeEvent.ON)
        }
    }

    fun deleteAction() {
        viewModelScope.launch {
            deleteMemoUseCase.execute(selectedSet.toList())
            selectedSet.clear()

            refresh()
            notifyActionEvent(ActionModeEvent.OFF)
        }
    }

    fun cancelAction() {
        viewModelScope.launch {
            selectedSet.clear()

            refresh()
            notifyActionEvent(ActionModeEvent.OFF)
        }
    }

    fun editAction() {
        _navEvent.value = NavEvent.EditMemo(selectedSet.first())
    }

    private fun notifyActionEvent(event: ActionModeEvent) {
        if (_actionMode.value != event) {
            _actionMode.value = event
        }
    }

    sealed class NavEvent {
        object NavigateMemo : NavEvent()
        data class EditMemo(val memo: MemoStatusView) : NavEvent()
    }
}
