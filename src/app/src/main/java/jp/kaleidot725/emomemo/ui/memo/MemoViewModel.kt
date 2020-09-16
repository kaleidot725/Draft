package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.ui.common.ActionModeEvent
import jp.kaleidot725.emomemo.usecase.CreateMessageUseCase
import jp.kaleidot725.emomemo.usecase.DeleteMessagesUseCase
import jp.kaleidot725.emomemo.usecase.GetMessageUseCase
import jp.kaleidot725.emomemo.usecase.ObserveMessageCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveRecognizedTextUseCase
import jp.kaleidot725.emomemo.usecase.ObserveStatusUseCase
import kotlinx.coroutines.launch

class MemoViewModel(
    private val observeStatusUseCase: ObserveStatusUseCase,
    private val observeMessageCountUseCase: ObserveMessageCountUseCase,
    private val observeRecognizedTextUseCase: ObserveRecognizedTextUseCase,
    private val createMessageUseCase: CreateMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val deleteMessagesUseCase: DeleteMessagesUseCase
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val inputMessage: MutableLiveData<String> = MutableLiveData()
    val isNotEmptyMessage: LiveData<Boolean> = inputMessage.map { it.isNotEmpty() }

    private val selectedSet: MutableSet<MessageEntity> = mutableSetOf()
    private val onSelected: MutableLiveData<Unit> = MutableLiveData()
    val selected: LiveData<Set<MessageEntity>> = onSelected.map { selectedSet }

    private val status: MutableLiveData<Pair<StatusEntity, Int>> = MutableLiveData()
    val messages: LiveData<PagedList<MessageEntity>> = status.switchMap { getMessageUseCase.execute(it.first.memoId) }.distinctUntilChanged()

    private val _actionMode: LiveEvent<ActionModeEvent> = LiveEvent<ActionModeEvent>().apply { value = ActionModeEvent.OFF }
    val actionMode: LiveData<ActionModeEvent> = _actionMode

    init {
        observeRecognizedTextUseCase.execute { recognizedMessage ->
            inputMessage.value = recognizedMessage
        }

        observeStatusUseCase.execute { newStatus ->
            newStatus ?: return@execute
            observeMessageCountUseCase.dispose()
            observeMessageCountUseCase.execute(newStatus.memoId) { count -> status.value = Pair(newStatus, count) }
        }
    }

    fun startAction(message: MessageEntity) {
        updateSelectedMessage(message)
    }

    fun deleteAction() {
        deleteSelectedMessages()
    }

    fun cancelAction() {
        clearSelectedMessages()
    }

    fun select(message: MessageEntity) {
        when (actionMode.value) {
            ActionModeEvent.ON -> updateSelectedMessage(message)
        }
    }

    fun create() {
        viewModelScope.launch {
            createMessageUseCase.execute(inputMessage.value ?: "")
            inputMessage.value = ""
        }
    }

    override fun onCleared() {
        observeRecognizedTextUseCase.dispose()
        observeStatusUseCase.dispose()
        observeMessageCountUseCase.dispose()
    }

    private fun updateSelectedMessage(message: MessageEntity) {
        viewModelScope.launch {
            if (selectedSet.contains(message)) selectedSet.remove(message) else selectedSet.add(message)
            notifyActionEvent(ActionModeEvent.ON)
            notifyChangedSelectedMemos()
        }
    }

    private fun deleteSelectedMessages() {
        viewModelScope.launch {
            deleteMessagesUseCase.execute(selectedSet.toList())
            notifyActionEvent(ActionModeEvent.OFF)
        }
    }

    private fun clearSelectedMessages() {
        viewModelScope.launch {
            selectedSet.clear()
            notifyChangedSelectedMemos()
            notifyActionEvent(ActionModeEvent.OFF)
        }
    }

    private fun notifyChangedSelectedMemos() {
        onSelected.value = Unit
    }

    private fun notifyActionEvent(event: ActionModeEvent) {
        if (_actionMode.value != event) {
            _actionMode.value = event
        }
    }
}
