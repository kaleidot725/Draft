package jp.kaleidot725.emomemo.ui.notebook

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentAddNotebookBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNotebookDialogFragment : DialogFragment(R.layout.fragment_add_notebook) {
    private val navController: NavController get() = findNavController()
    private val binding: FragmentAddNotebookBinding by viewBinding()
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val addNotebookViewModel: AddNotebookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainViewModel = mainViewModel
        binding.addNotebookViewModel = addNotebookViewModel
        addNotebookViewModel.event.observe(viewLifecycleOwner, Observer { event ->
            when (event) {
                AddNotebookViewModel.NavEvent.SUCCESS -> {
                    mainViewModel.createNotebook(addNotebookViewModel.title.value ?: "")
                    navController.popBackStack()
                }
                AddNotebookViewModel.NavEvent.CANCEL -> {
                    navController.popBackStack()
                }
                else -> {
                    navController.popBackStack()
                }
            }
        })
    }
}
