package jp.kaleidot725.emomemo.ui.homedialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentHomeDialogBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeDialogFragment : DialogFragment(R.layout.fragment_home_dialog) {
    private val navController: NavController get() = findNavController()
    private val binding: FragmentHomeDialogBinding by viewBinding()
    private val viewModel: HomeDialogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel
        viewModel.event.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeDialogViewModel.NavEvent.SUCCESS -> navController.popBackStack()
                HomeDialogViewModel.NavEvent.CANCEL -> navController.popBackStack()
            }
        })
    }
}
