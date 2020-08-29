package jp.kaleidot725.emomemo.ui.notebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.usecase.CreateNotebookUseCase
import kotlinx.coroutines.launch

class AddNotebookViewModel(private val createNotebookUseCase: CreateNotebookUseCase) : ViewModel() {
    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event
    val title: MutableLiveData<String> = MutableLiveData()

    fun success() {
        viewModelScope.launch {
            val value = title.value ?: ""
            if (value.isNotEmpty()) {
                createNotebookUseCase.execute(value)
                _event.postValue(NavEvent.SUCCESS)
            }
        }
    }

    fun cancel() {
        _event.postValue(NavEvent.CANCEL)
    }

    enum class NavEvent {
        SUCCESS,
        CANCEL
    }
}
