package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.extension.getValueSafety
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.model.db.repository.OnChangedRecognizedTextListener
import jp.kaleidot725.emomemo.model.ddd.domain.Message
import jp.kaleidot725.emomemo.model.ddd.domainService.MessageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MemoViewModel(
    private val messageService: MessageService,
    private val audioRecognizerRepository: AudioRecognizerRepository
) : ViewModel() {
    private var memoId: Int = 0
    private val refresh: MutableLiveData<Unit> = MutableLiveData()
    val messages: LiveData<List<Message>> = refresh.switchMap {
        liveData(Dispatchers.IO) {
            emit(messageService.getMessage(memoId))
        }
    }
    val inputMessage: MutableLiveData<String> = MutableLiveData()

    private val onChangedRecognizedTextListener: OnChangedRecognizedTextListener = object : OnChangedRecognizedTextListener {
        override fun onChanged(text: String) {
            inputMessage.postValue(inputMessage.value + text)
        }
    }

    init {
        audioRecognizerRepository.addOnChangedRecognizedTextListener(onChangedRecognizedTextListener)
    }

    fun refresh(id: Int) {
        memoId = id
        refresh.value = Unit
        inputMessage.value = ""
    }

    fun create() {
        viewModelScope.launch(Dispatchers.IO) {
            val message = inputMessage.getValueSafety("")
            if (message.isNotEmpty()) {
                messageService.create(memoId, inputMessage.getValueSafety(""))
                withContext(Dispatchers.Main) {
                    refresh(memoId)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioRecognizerRepository.removeOnChangedRecognizedTextListener(onChangedRecognizedTextListener)
    }
}
