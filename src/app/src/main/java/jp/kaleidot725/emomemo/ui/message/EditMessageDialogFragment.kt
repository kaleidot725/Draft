package jp.kaleidot725.emomemo.ui.message

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentEditMessasgeBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditMessageDialogFragment : DialogFragment(R.layout.fragment_edit_messasge) {
    private val args: EditMessageDialogFragmentArgs by navArgs()
    private val navController: NavController get() = findNavController()
    private val binding: FragmentEditMessasgeBinding by viewBinding()
    private val viewModel: EditMessageDialogViewModel by viewModel { parametersOf(args.message) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.isCompleted.observe(viewLifecycleOwner, Observer { navController.popBackStack() })
    }
}
