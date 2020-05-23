package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.model.ddd.domain.Message
import jp.kaleidot725.emomemo.model.ddd.domainService.MessageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(private val messageService: MessageService) : ViewModel() {
    private var memoId: Int = 0

    private val _refresh: MutableLiveData<Unit> = MutableLiveData()
    val messageList: LiveData<List<Message>> = _refresh.switchMap {
        liveData(Dispatchers.IO) {
            emit(messageService.getMessage(memoId))
        }
    }

    val message: MutableLiveData<String> = MutableLiveData()

    fun fetchData(memoId: Int) {
        this.memoId = memoId
        _refresh.value = Unit
    }

    fun add() {
        viewModelScope.launch(Dispatchers.IO) {
            val message = this@MemoViewModel.message.value ?: ""
            messageService.create(memoId, message)
            withContext(Dispatchers.Main) {
                _refresh.value = Unit
            }
        }
    }
}
