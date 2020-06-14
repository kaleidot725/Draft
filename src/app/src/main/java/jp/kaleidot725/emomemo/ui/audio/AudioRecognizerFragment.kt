package jp.kaleidot725.emomemo.ui.audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentAudioRecognizerBinding
import jp.kaleidot725.emomemo.ui.common.inflateDB
import org.koin.androidx.viewmodel.ext.android.viewModel

class AudioRecognizerFragment : DialogFragment() {
    private val navController: NavController get() = findNavController()
    private val viewModel: AudioRecognizerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateDB<FragmentAudioRecognizerBinding>(container, R.layout.fragment_audio_recognizer, false)
            .also { binding ->
                binding.lifecycleOwner = this.viewLifecycleOwner
                binding.viewModel = this.viewModel
            }.root
    }
}
