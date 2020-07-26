package jp.kaleidot725.emomemo.ui.audio

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentAudioRecordBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.common.controller.SpeechRecognizerController
import jp.kaleidot725.emomemo.ui.common.handler.SafetyHandler
import org.koin.androidx.viewmodel.ext.android.viewModel

class AudioRecordFragment : DialogFragment(R.layout.fragment_audio_record) {
    private val viewModel: AudioRecordViewModel by viewModel()
    private val binding: FragmentAudioRecordBinding by viewBinding()
    private lateinit var hidingFragmentHandler: SafetyHandler
    private lateinit var speechRecognizerController: SpeechRecognizerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hidingFragmentHandler = SafetyHandler(Runnable {
            try {
                findNavController().popBackStack()
            } catch (e: Exception) {
                Log.e("AudioRecordFragment", e.toString())
            }
        })

        speechRecognizerController = SpeechRecognizerController(this.context) { event, text ->
            viewModel.update(event, text)
        }

        binding.let {
            it.viewModel = viewModel
            it.floatingActionButton.setOnClickListener { speechRecognizerController.retry() }
        }

        lifecycle.addObserver(speechRecognizerController)
        lifecycle.addObserver(hidingFragmentHandler)

        viewModel.shouldHide.observe(viewLifecycleOwner, Observer {
            if (it) {
                hidingFragmentHandler.postDelayed(HIDE_DELAY_DURATION)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        speechRecognizerController.start()
    }

    override fun onPause() {
        super.onPause()
        speechRecognizerController.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(speechRecognizerController)
        lifecycle.removeObserver(hidingFragmentHandler)
    }

    companion object {
        private const val HIDE_DELAY_DURATION = 1000L
    }
}
