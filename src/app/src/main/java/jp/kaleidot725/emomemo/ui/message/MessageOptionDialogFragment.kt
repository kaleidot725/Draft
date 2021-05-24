package jp.kaleidot725.emomemo.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentMessageOptionBinding
import jp.kaleidot725.emomemo.ui.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageOptionDialogFragment : BottomSheetDialogFragment() {
    private val navController: NavController get() = findNavController()
    private val viewModel: MessageOptionDialogViewModel by viewModel()
    private val binding: FragmentMessageOptionBinding by viewBinding()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message_option, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        viewModel.navEvent.observe(viewLifecycleOwner) {
            when (it) {
                MessageOptionDialogViewModel.NavEvent.NavigateEdittingMessage -> {
                    navController.popBackStack()
                    navController.navigate(R.id.action_global_editMessageDialogFragment)
                }
                MessageOptionDialogViewModel.NavEvent.NavigateDeletingMessage -> {
                    navController.popBackStack()
                }
            }
        }
    }
}