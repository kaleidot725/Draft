package jp.kaleidot725.emomemo.ui.homedialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.AppStatus
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository

class HomeDialogViewModel(
    private val appStatus: AppStatus,
    private val memoRepository: MemoRepository
) : ViewModel() {
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
