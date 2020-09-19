package jp.kaleidot725.emomemo.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.usecase.UpdateMessageUseCase
import kotlinx.coroutines.launch

class EditMessageDialogViewModel(
    private val message: MessageEntity,
    private val updateMessageUseCase: UpdateMessageUseCase
) : ViewModel() {
    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    val inputTitle: MutableLiveData<String> = MutableLiveData(message.value)
    private var inputtedTitle: String = message.value
    private val inputTitleObserver: Observer<String> = Observer { inputtedTitle = it }

    init {
        inputTitle.observeForever(inputTitleObserver)
    }

    override fun onCleared() {
        inputTitle.removeObserver(inputTitleObserver)
    }

    fun success() {
        if (inputtedTitle.isEmpty()) {
            return
        }

        viewModelScope.launch {
            updateMessageUseCase.execute(message, inputtedTitle)
            complete()
        }
    }

    fun cancel() {
        complete()
    }

    private fun complete() {
        _isCompleted.value = true
    }
}
