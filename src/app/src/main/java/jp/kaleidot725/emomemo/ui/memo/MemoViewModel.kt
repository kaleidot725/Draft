package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.*
import jp.kaleidot725.emomemo.model.entity.Message
import jp.kaleidot725.emomemo.model.repository.MessageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(private val messageRepository: MessageRepository) : ViewModel() {
    private var memoId: Int = 0

    private val _refresh: MutableLiveData<Unit> = MutableLiveData()
    val messageList: LiveData<List<Message>> = _refresh.switchMap {
        liveData(Dispatchers.IO) {
            emit(messageRepository.getMessage(memoId))
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
            messageRepository.create(memoId, message)
            withContext(Dispatchers.Main) {
                _refresh.value = Unit
            }
        }
    }
}
