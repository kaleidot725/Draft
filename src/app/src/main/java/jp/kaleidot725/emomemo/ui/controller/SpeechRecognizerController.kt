package jp.kaleidot725.emomemo.ui.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class SpeechRecognizerController(
    private val context: Context,
    private val onEvent: (event: String, text: String) -> Unit
) : DefaultLifecycleObserver {
    private lateinit var speechRecognizer: SpeechRecognizer

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(createRecognitionListener(onEvent))
    }

    fun start() {
        speechRecognizer.startListening(Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH))
    }

    fun cancel() {
        speechRecognizer.stopListening()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        speechRecognizer.destroy()
    }

    private fun createRecognitionListener(onEvent: (event: String, text: String) -> Unit): RecognitionListener {
        return object : RecognitionListener {
            override fun onRmsChanged(rmsdB: Float) {}

            override fun onReadyForSpeech(params: Bundle) {
                onEvent("onReadyForSpeech", "")
            }

            override fun onBufferReceived(buffer: ByteArray) {
                onEvent("onBufferReceived", "")
            }

            override fun onPartialResults(partialResults: Bundle) {
                onEvent("onPartialResults", "")
            }

            override fun onEvent(eventType: Int, params: Bundle) {
                onEvent("onEvent", "")
            }

            override fun onBeginningOfSpeech() {
                onEvent("onBeginningOfSpeech", "")
            }

            override fun onEndOfSpeech() {
                onEvent("onEndOfSpeech", "")
            }

            override fun onError(error: Int) {
                onEvent("onError", "")
            }

            override fun onResults(results: Bundle) {
                val stringArray = results.getStringArrayList(android.speech.SpeechRecognizer.RESULTS_RECOGNITION);
                onEvent("onResults " + stringArray.toString(), stringArray.toString())
            }
        }
    }
}
