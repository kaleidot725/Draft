package jp.kaleidot725.emomemo.ui.notebook

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentEditNotebookBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditNotebookDialogFragment : DialogFragment(R.layout.fragment_edit_notebook) {
    private val args: EditNotebookDialogFragmentArgs by navArgs()
    private val navController: NavController get() = findNavController()
    private val binding: FragmentEditNotebookBinding by viewBinding()
    private val viewModel: EditNotebookDialogViewModel by viewModel { parametersOf(args.notebook) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.isCompleted.observe(viewLifecycleOwner, Observer {
            navController.popBackStack(R.id.homeFragment, true)
            navController.navigate(R.id.homeFragment)
        })
    }
}
