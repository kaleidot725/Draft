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
import jp.kaleidot725.emomemo.usecase.ObserveMemoCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectMemoUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeStatusUseCase: ObserveStatusUseCase,
    private val observeMemoCountUseCase: ObserveMemoCountUseCase,
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

    private val status: MutableLiveData<Pair<StatusEntity, Int>> = MutableLiveData()
    val memos: LiveData<PagedList<MemoStatusView>> = status.switchMap { getMemoUseCase.execute(it.first.notebookId) }.distinctUntilChanged()

    private val selectedSet: MutableSet<MemoStatusView> = mutableSetOf()
    private val onSelected: MutableLiveData<Unit> = MutableLiveData()
    val selected: LiveData<Set<MemoStatusView>> = onSelected.map { selectedSet }

    private val _actionMode: LiveEvent<ActionModeEvent> = LiveEvent<ActionModeEvent>().apply { value = ActionModeEvent.OFF }
    val actionMode: LiveData<ActionModeEvent> = _actionMode

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    init {
        observeStatusUseCase.execute { newStatus ->
            newStatus ?: return@execute
            observeMemoCountUseCase.dispose()
            observeMemoCountUseCase.execute(newStatus.notebookId) { count ->
                status.value = Pair(newStatus, count)
                cancelAction()
            }
        }
    }

    fun startAction(memo: MemoStatusView) {
        updateSelectedMemoForAction(memo)
    }

    fun deleteAction() {
        deleteSelectedMemos()
    }

    fun cancelAction() {
        clearSelectedMemos()
    }

    fun select(memo: MemoStatusView) {
        when (actionMode.value) {
            ActionModeEvent.ON -> updateSelectedMemoForAction(memo)
            ActionModeEvent.OFF -> navigateMemoDetails(memo)
        }
    }

    override fun onCleared() {
        observeStatusUseCase.dispose()
        observeMemoCountUseCase.dispose()
    }

    private fun navigateMemoDetails(memo: MemoStatusView) {
        viewModelScope.launch {
            selectMemoUseCase.execute(memo.id)
            notifyActionEvent(ActionModeEvent.OFF)
            notifyNavEvent(NavEvent.NAVIGATE_MEMO)
        }
    }

    private fun updateSelectedMemoForAction(memo: MemoStatusView) {
        viewModelScope.launch {
            selectedSet.clear()
            selectedSet.add(memo)
            notifyActionEvent(ActionModeEvent.ON)
            notifyChangedSelectedMemos()
        }
    }

    private fun deleteSelectedMemos() {
        viewModelScope.launch {
            deleteMemoUseCase.execute(selectedSet.toList())
            notifyActionEvent(ActionModeEvent.OFF)
        }
    }

    private fun clearSelectedMemos() {
        viewModelScope.launch {
            selectedSet.clear()
            notifyChangedSelectedMemos()
            notifyActionEvent(ActionModeEvent.OFF)
        }
    }

    private fun notifyChangedSelectedMemos() {
        onSelected.value = Unit
    }

    private fun notifyNavEvent(event: NavEvent) {
        _navEvent.value = event
    }

    private fun notifyActionEvent(event: ActionModeEvent) {
        if (_actionMode.value != event) {
            _actionMode.value = event
        }
    }

    enum class NavEvent {
        NAVIGATE_MEMO
    }
}
