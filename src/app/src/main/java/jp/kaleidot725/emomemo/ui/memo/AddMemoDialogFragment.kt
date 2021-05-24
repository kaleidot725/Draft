package jp.kaleidot725.emomemo.ui.memo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentAddMemoBinding
import jp.kaleidot725.emomemo.ui.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMemoDialogFragment : DialogFragment(R.layout.fragment_add_memo) {
    private val navController: NavController get() = findNavController()
    private val binding: FragmentAddMemoBinding by viewBinding()
    private val addMemoDialogViewModel: AddMemoDialogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMemoDialogViewModel = addMemoDialogViewModel
        addMemoDialogViewModel.isCompleted.observe(viewLifecycleOwner, Observer { navController.popBackStack() })
    }
}
