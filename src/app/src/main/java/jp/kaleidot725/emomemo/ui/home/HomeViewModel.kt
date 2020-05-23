package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.ddd.domain.Memo
import jp.kaleidot725.emomemo.model.ddd.domainService.MemoService
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val memoService: MemoService) : ViewModel() {
    private val _refresh: MutableLiveData<Unit> = MutableLiveData()

    val memoList: LiveData<List<Memo>> = _refresh.switchMap {
        liveData(Dispatchers.IO) {
            emit(memoService.getAll())
        }
    }

    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event

    fun fetchData() {
        _refresh.value = Unit
    }

    fun add() {
        _event.postValue(NavEvent.ADD)
    }

    enum class NavEvent {
        ADD
    }
}
