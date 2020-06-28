package jp.kaleidot725.emomemo.ui.audio

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentAudioRecordBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.controller.SpeechRecognizerController
import org.koin.androidx.viewmodel.ext.android.viewModel

class AudioRecordFragment : DialogFragment(R.layout.fragment_audio_record) {
    private val viewModel: AudioRecordViewModel by viewModel()
    private val binding: FragmentAudioRecordBinding by viewBinding()
    private val speechRecognizerController: SpeechRecognizerController by lazy {
        SpeechRecognizerController(this.context) { event, text ->
            viewModel.update(event, text)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
        this.lifecycle.addObserver(speechRecognizerController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel

        speechRecognizerController.start()
        this.viewModel.event.observe(viewLifecycleOwner, Observer {
            speechRecognizerController.retry()
        })
    }
}
