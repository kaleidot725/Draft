package jp.kaleidot725.emomemo.ui.audio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.ui.controller.SpeechRecognizerController

class AudioRecordViewModel : ViewModel() {
    private val _event: LiveEvent<SpeechRecognizerController.RecognizeEvent> = LiveEvent()
    val event: LiveData<SpeechRecognizerController.RecognizeEvent> = _event

    private val _fabResId: MutableLiveData<Int> = MutableLiveData()
    val fabResId: LiveData<Int> = _fabResId

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    fun update(event: SpeechRecognizerController.RecognizeEvent, message: String) {
        _message.value = message
        _fabResId.value = event.getFabResId()
    }

    fun retry() {
        _event.value = SpeechRecognizerController.RecognizeEvent.RECOGNITION_RESTART
    }

    private fun SpeechRecognizerController.RecognizeEvent.getFabResId(): Int {
        return if (this.isRecognizing()) {
            R.drawable.ic_thinking
        } else {
            R.drawable.ic_mic
        }
    }

    private fun SpeechRecognizerController.RecognizeEvent.isRecognizing(): Boolean {
        return (this != SpeechRecognizerController.RecognizeEvent.RECOGNITION_SUCCESS && this != SpeechRecognizerController.RecognizeEvent.RECOGNITION_FAILED)
    }


}
