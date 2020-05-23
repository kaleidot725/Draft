package jp.kaleidot725.emomemo.ui.homedialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.ddd.domainService.MemoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeDialogViewModel(private val memoService: MemoService) : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData()
    val tag: MutableLiveData<String> = MutableLiveData()

    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event

    fun success() {
        viewModelScope.launch {
            if (title.value != null && tag.value != null) {
                withContext(Dispatchers.IO) {
                    memoService.create(tag.value ?: "", title.value ?: "")
                }

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
