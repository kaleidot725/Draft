package jp.kaleidot725.emomemo.ui.homedialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent

class HomeDialogViewModel : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData()
    val tag: MutableLiveData<String> = MutableLiveData()

    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event

    fun success() {
        // TODO タスクを生成する処理を実装する
        _event.postValue(NavEvent.SUCCESS)
    }

    fun cancel() {
        _event.postValue(NavEvent.CANCEL)
    }

    enum class NavEvent {
        SUCCESS,
        CANCEL
    }
}
