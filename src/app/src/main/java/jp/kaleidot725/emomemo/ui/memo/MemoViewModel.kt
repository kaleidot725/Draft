package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.extension.getValueSafety
import jp.kaleidot725.emomemo.model.ddd.domain.Message
import jp.kaleidot725.emomemo.model.ddd.domainService.MessageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(private val messageService: MessageService) : ViewModel() {
    private val refresh: MutableLiveData<Unit> = MutableLiveData()

    var memoId: Int = 0
    val message: MutableLiveData<String> = MutableLiveData()
    val messageList: LiveData<List<Message>> = refresh.switchMap {
        liveData(Dispatchers.IO) {
            emit(messageService.getMessage(memoId))
        }
    }

    fun fetch() {
        refresh.value = Unit
        this@MemoViewModel.message.value = ""
    }

    fun create() {
        viewModelScope.launch(Dispatchers.IO) {
            messageService.create(memoId, message.getValueSafety(""))
            withContext(Dispatchers.Main) { fetch() }
        }
    }
}
