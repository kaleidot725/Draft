package jp.kaleidot725.emomemo.usecase.observe

class ObserveRecognizedTextUseCase(private val audioRecognizerRepository: jp.kaleidot725.emomemo.data.repository.AudioRecognizerRepository) {
    private var listener: jp.kaleidot725.emomemo.data.repository.OnChangedRecognizedTextListener? = null

    fun execute(block: (String) -> Unit) {
        listener = object : jp.kaleidot725.emomemo.data.repository.OnChangedRecognizedTextListener {
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
