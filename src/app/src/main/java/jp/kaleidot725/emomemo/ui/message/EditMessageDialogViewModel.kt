package jp.kaleidot725.emomemo.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.domain.usecase.select.GetSelectedMessageUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.UpdateSelectedMessageUseCase
import kotlinx.coroutines.launch

class EditMessageDialogViewModel(
    private val getSelectedMessageUseCase: GetSelectedMessageUseCase,
    private val updateSelectedMessageUseCase: UpdateSelectedMessageUseCase
) : ViewModel() {
    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    val inputTitle: MutableLiveData<String> = MutableLiveData()
    private var inputtedTitle: String = ""
    private val inputTitleObserver: Observer<String> = Observer { inputtedTitle = it }

    init {
        viewModelScope.launch {
            getSelectedMessageUseCase.execute()?.let { memo ->
                inputTitle.value = memo.value
                inputTitle.observeForever(inputTitleObserver)
            }
        }
    }

    override fun onCleared() {
        inputTitle.removeObserver(inputTitleObserver)
    }

    fun success() {
        if (inputtedTitle.isEmpty()) {
            return
        }

        viewModelScope.launch {
            updateSelectedMessageUseCase.execute(inputtedTitle)
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
