package jp.kaleidot725.emomemo.domain.usecase.observe

import jp.kaleidot725.emomemo.data.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.data.repository.OnChangedRecognizedTextListener

class ObserveRecognizedTextUseCase(private val audioRecognizerRepository: AudioRecognizerRepository) {
    private var listener: OnChangedRecognizedTextListener? = null

    fun execute(block: (String) -> Unit) {
        listener = object : OnChangedRecognizedTextListener {
            override fun onChanged(text: String) {
                block.invoke(text)
            }
        }
        audioRecognizerRepository.addOnChangedRecognizedTextListener(listener!!)
    }

    fun dispose() {
        if (listener != null) {
            audioRecognizerRepository.removeOnChangedRecognizedTextListener(listener!!)
        }
    }
}
