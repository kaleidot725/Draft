package jp.kaleidot725.emomemo.ui.audio

import android.net.Uri
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentAudioRecordBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.common.controller.SpeechRecognizerController
import jp.kaleidot725.emomemo.ui.common.handler.SafetyHandler
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

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
                Timber.e(e)
            }
        })

        speechRecognizerController = SpeechRecognizerController(this.context) { event, text ->
            viewModel.update(event, text)
        }

        lifecycle.addObserver(speechRecognizerController)
        lifecycle.addObserver(hidingFragmentHandler)

        binding.viewModel = viewModel
        binding.imageView.loadGifImage(getRecordGifImage())
        
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

    private fun ImageView.loadGifImage(url: Uri) {
        val imageLoader = ImageLoader.Builder(requireContext()).componentRegistry {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder())
            } else {
                add(GifDecoder())
            }
        }.build()
        this.load(url, imageLoader)
    }

    private fun getRecordGifImage(): Uri {
        return Uri.parse("android.resource://" + requireActivity().packageName + "/" + R.raw.record)
    }

    companion object {
        private const val HIDE_DELAY_DURATION = 1000L
    }
}
