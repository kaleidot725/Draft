package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.ui.common.ActionModeEvent
import jp.kaleidot725.emomemo.ui.home.SingleSelectList
import jp.kaleidot725.emomemo.usecase.CreateMessageUseCase
import jp.kaleidot725.emomemo.usecase.DeleteMessagesUseCase
import jp.kaleidot725.emomemo.usecase.GetMessageUseCase
import jp.kaleidot725.emomemo.usecase.GetStatusUseCase
import kotlinx.coroutines.launch

data class MessageWithSelectedSet(
    val messages: PagedList<MessageEntity>,
    val selectedMessages: List<MessageEntity>
)

class MemoViewModel(
    private val getStatusUseCase: GetStatusUseCase,
    private val createMessageUseCase: CreateMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val deleteMessagesUseCase: DeleteMessagesUseCase
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _actionMode: LiveEvent<ActionModeEvent> = LiveEvent<ActionModeEvent>().apply { value = ActionModeEvent.OFF }
    val actionMode: LiveData<ActionModeEvent> = _actionMode

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    private val selectedMessages: SingleSelectList<MessageEntity> = SingleSelectList()

    private val status: MutableLiveData<StatusEntity> = MutableLiveData()
    private val messages: LiveData<PagedList<MessageEntity>> = status.switchMap { getMessageUseCase.execute(it.memoId) }
    val messagesWithSelectedSet: LiveData<MessageWithSelectedSet> = messages.map { MessageWithSelectedSet(it, selectedMessages.getList()) }

    val inputMessage: MutableLiveData<String> = MutableLiveData()
    val isNotEmptyMessage: LiveData<Boolean> = inputMessage.map { it.isNotEmpty() }

    fun refresh() {
        viewModelScope.launch {
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.OFF
        }
    }

    fun select(message: MessageEntity) {
        viewModelScope.launch {
            if (actionMode.value == ActionModeEvent.ON) {
                selectedMessages.add(message)
                status.value = getStatusUseCase.execute()
            }
        }
    }

    fun create() {
        viewModelScope.launch {
            createMessageUseCase.execute(inputMessage.value ?: "")
            inputMessage.value = ""
            status.value = getStatusUseCase.execute()
        }
    }

    fun startAction(message: MessageEntity) {
        viewModelScope.launch {
            selectedMessages.add(message)
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.ON
        }
    }

    fun deleteAction() {
        viewModelScope.launch {
            deleteMessagesUseCase.execute(selectedMessages.getList())
            selectedMessages.clear()
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.OFF
        }
    }

    fun cancelAction() {
        viewModelScope.launch {
            selectedMessages.clear()
            status.value = getStatusUseCase.execute()
            _actionMode.value = ActionModeEvent.OFF
        }
    }

    fun editAction() {
        _navEvent.value = NavEvent.NavigateEditMessage(selectedMessages.get())
    }

    sealed class NavEvent {
        data class NavigateEditMessage(val message: MessageEntity) : NavEvent()
    }
}
