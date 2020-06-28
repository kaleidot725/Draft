package jp.kaleidot725.emomemo.ui.audio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.ui.controller.SpeechRecognizerController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AudioRecordViewModel(private val audioRecognizerRepository: AudioRecognizerRepository) : ViewModel() {
    private val _event: LiveEvent<SpeechRecognizerController.RecognizeEvent> = LiveEvent()
    val event: LiveData<SpeechRecognizerController.RecognizeEvent> = _event
    val fabResId: LiveData<Int> = event.map { it.getFabResId() }

    private val _shouldHide: LiveEvent<Boolean> = LiveEvent()
    val shouldHide: LiveData<Boolean> = _shouldHide

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    fun update(event: SpeechRecognizerController.RecognizeEvent, message: String) {
        _event.value = event
        _message.value = message

        // 音声認識が成功したら、認識した文字を保存して画面を閉じる
        if (event.shouldHide()) {
            viewModelScope.launch(Dispatchers.IO) {
                audioRecognizerRepository.save(message)
                _shouldHide.postValue(true)
            }
        }
    }

    private fun SpeechRecognizerController.RecognizeEvent.getFabResId(): Int {
        return if (this.isRecognizing()) {
            R.drawable.ic_thinking
        } else {
            R.drawable.ic_mic
        }
    }

    private fun SpeechRecognizerController.RecognizeEvent.shouldHide(): Boolean {
        return (this == SpeechRecognizerController.RecognizeEvent.RECOGNITION_SUCCESS)
    }

    private fun SpeechRecognizerController.RecognizeEvent.isRecognizing(): Boolean {
        return (this != SpeechRecognizerController.RecognizeEvent.RECOGNITION_SUCCESS && this != SpeechRecognizerController.RecognizeEvent.RECOGNITION_FAILED)
    }
}
