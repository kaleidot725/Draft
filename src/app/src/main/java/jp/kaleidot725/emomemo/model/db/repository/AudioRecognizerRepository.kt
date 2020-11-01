package jp.kaleidot725.emomemo.model.db.repository

class AudioRecognizerRepository {
    private val listeners: MutableList<OnChangedRecognizedTextListener> = mutableListOf()
    private var text: String = ""

    fun save(text: String) {
        this.text = text
        notifyOnChangedText()
    }

    fun clear() {
        this.text = ""
    }

    fun addOnChangedRecognizedTextListener(listener: OnChangedRecognizedTextListener) {
        listeners.add(listener)
    }

    fun removeOnChangedRecognizedTextListener(listener: OnChangedRecognizedTextListener) {
        listeners.remove(listener)
    }

    private fun notifyOnChangedText() {
        listeners.forEach { it.onChanged(text) }
    }
}

interface OnChangedRecognizedTextListener {
    fun onChanged(text: String)
}
