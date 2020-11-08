package jp.kaleidot725.emomemo.ui.audio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.ui.common.controller.SpeechRecognizerController

class AudioRecordViewModel(private val audioRecognizerRepository: AudioRecognizerRepository) : ViewModel() {
    private val _event: LiveEvent<SpeechRecognizerController.RecognizeEvent> = LiveEvent()
    val shouldHide: LiveData<Boolean> = _event.map { it.shouldHide() }

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    fun update(event: SpeechRecognizerController.RecognizeEvent, message: String) {
        if (event == SpeechRecognizerController.RecognizeEvent.RECOGNITION_SUCCESS) {
            audioRecognizerRepository.save(message)
        }

        _event.value = event
        _message.value = message
    }

    private fun SpeechRecognizerController.RecognizeEvent.shouldHide(): Boolean {
        return (this == SpeechRecognizerController.RecognizeEvent.RECOGNITION_SUCCESS) ||
                (this == SpeechRecognizerController.RecognizeEvent.RECOGNITION_FAILED)
    }
}
