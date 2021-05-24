package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.data.entity.StatusEntity
import jp.kaleidot725.emomemo.domain.usecase.create.CreateMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMessageCountUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.get.GetStatusUseCase
import jp.kaleidot725.emomemo.domain.usecase.observe.ObserveRecognizedTextUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.SelectMessageUseCase
import jp.kaleidot725.emomemo.ui.common.SingleSelectList
import kotlinx.coroutines.launch

data class MessageWithSelectedSet(
    val messages: PagedList<MessageEntity>,
    val selectedMessages: List<MessageEntity>
)

class MemoViewModel(
    private val getStatusUseCase: GetStatusUseCase,
    private val selectMessageUseCase: SelectMessageUseCase,
    private val createMessageUseCase: CreateMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getMessageCountUseCase: GetMessageCountUseCase,
    private val observeRecognizedTextUseCase: ObserveRecognizedTextUseCase
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    private val selectedMessages: SingleSelectList<MessageEntity> = SingleSelectList()

    private val status: MutableLiveData<StatusEntity> = MutableLiveData()
    private val messages: LiveData<PagedList<MessageEntity>> =
        status.switchMap { getMessageUseCase.execute(it.memoId) }
    val messagesWithSelectedSet: LiveData<MessageWithSelectedSet> = messages.map { MessageWithSelectedSet(it, selectedMessages.getList()) }

    val inputMessage: MutableLiveData<String> = MutableLiveData()
    val isNotEmptyMessage: LiveData<Boolean> = inputMessage.map { it.isNotEmpty() }

    private val messagesCount: LiveData<Int> = status.switchMap { getMessageCountUseCase.execute(it.memoId) }
    val messagesAreEmpty: LiveData<Boolean> = messagesCount.map { it == 0 }

    init {
        observeRecognizedTextUseCase.execute {
            inputMessage.postValue(it)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            status.value = getStatusUseCase.execute()
        }
    }

    fun longTap(message: MessageEntity) {
        viewModelScope.launch {
            selectMessageUseCase.execute(message.id)
            _navEvent.value = NavEvent.NavigateMessageOption
        }
    }

    fun create() {
        viewModelScope.launch {
            createMessageUseCase.execute(inputMessage.value ?: "")
            inputMessage.value = ""
            status.value = getStatusUseCase.execute()
        }
    }

    override fun onCleared() {
        super.onCleared()
        observeRecognizedTextUseCase.dispose()
    }

    sealed class NavEvent {
        object NavigateMessageOption : NavEvent()
    }
}
