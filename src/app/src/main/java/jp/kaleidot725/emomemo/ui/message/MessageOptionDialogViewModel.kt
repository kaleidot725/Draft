package jp.kaleidot725.emomemo.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.usecase.select.DeleteSelectedMessageUseCase
import jp.kaleidot725.emomemo.usecase.select.GetSelectedMessageUseCase
import kotlinx.coroutines.launch

class MessageOptionDialogViewModel(
    private val getSelectedMessageUseCase: GetSelectedMessageUseCase,
    private val deleteSelectedMessageUseCase: DeleteSelectedMessageUseCase
) : ViewModel() {
    private val _message: MutableLiveData<jp.kaleidot725.emomemo.data.entity.MessageEntity> = MutableLiveData()
    val message: LiveData<jp.kaleidot725.emomemo.data.entity.MessageEntity> = _message

    private val _navEvent: LiveEvent<NavEvent> = LiveEvent()
    val navEvent: LiveData<NavEvent> = _navEvent

    init {
        viewModelScope.launch {
            _message.value = getSelectedMessageUseCase.execute()
        }
    }

    fun edit() {
        viewModelScope.launch {
            _navEvent.value = NavEvent.NavigateEdittingMessage
        }
    }

    fun delete() {
        viewModelScope.launch {
            deleteSelectedMessageUseCase.execute()
            _navEvent.value = NavEvent.NavigateDeletingMessage
        }
    }

    sealed class NavEvent {
        object NavigateEdittingMessage : NavEvent()
        object NavigateDeletingMessage : NavEvent()
    }
}