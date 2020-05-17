package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.entity.Memo
import jp.kaleidot725.emomemo.model.repository.DUMMY_MEMO_LIST
import jp.kaleidot725.emomemo.model.repository.MemoRepository
import kotlinx.coroutines.Dispatchers

class HomeViewModel(memoRepository: MemoRepository) : ViewModel() {
    val memoList: LiveData<List<Memo>> = liveData(Dispatchers.IO) {
        emit(memoRepository.getAll() + DUMMY_MEMO_LIST)
    }

    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event

    fun add() {
        _event.postValue(NavEvent.ADD)
    }

    enum class NavEvent {
        ADD
    }
}
