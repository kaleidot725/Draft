package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.model.db.repository.OnChangedRecognizedTextListener

class MemoViewModel(
    private val audioRecognizerRepository: AudioRecognizerRepository
) : ViewModel() {
    val inputMessage: MutableLiveData<String> = MutableLiveData()
    val isNotEmptyMessage: LiveData<Boolean> = inputMessage.map { it.isNotEmpty() }

    private val onChangedRecognizedTextListener: OnChangedRecognizedTextListener = object : OnChangedRecognizedTextListener {
        override fun onChanged(text: String) {
            inputMessage.postValue(inputMessage.value + text)
        }
    }

    init {
        audioRecognizerRepository.addOnChangedRecognizedTextListener(onChangedRecognizedTextListener)
    }

    fun reset() {
        inputMessage.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        audioRecognizerRepository.removeOnChangedRecognizedTextListener(onChangedRecognizedTextListener)
    }
}
