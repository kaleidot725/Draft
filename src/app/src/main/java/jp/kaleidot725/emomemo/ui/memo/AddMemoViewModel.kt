package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent

class AddMemoViewModel : ViewModel() {
    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event
    val title: MutableLiveData<String> = MutableLiveData()

    fun success() {
        if (title.value?.isNotEmpty() == true) {
            _event.postValue(NavEvent.SUCCESS)
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
