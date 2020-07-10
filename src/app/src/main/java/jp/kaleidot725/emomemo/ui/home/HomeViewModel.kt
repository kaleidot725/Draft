package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val memoRepository: MemoStatusRepository) : ViewModel() {
    private val _refresh: MutableLiveData<Unit> = MutableLiveData(Unit)
    val memoList: LiveData<List<MemoStatusView>> = _refresh.switchMap {
        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(memoRepository.getAll())
        }
    }

    fun refresh() {
        _refresh.value = Unit
    }
}
