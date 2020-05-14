package jp.kaleidot725.emomemo.ui.homedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentHomeDialogBinding
import jp.kaleidot725.emomemo.ui.common.inflateDB
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeDialogFragment : DialogFragment() {
    private val navController: NavController get() = findNavController()
    private val viewModel: HomeDialogViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateDB<FragmentHomeDialogBinding>(container, R.layout.fragment_home_dialog, false).apply {
            this.lifecycleOwner = this@HomeDialogFragment
            this.viewModel = this@HomeDialogFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.event.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeDialogViewModel.NavEvent.SUCCESS, HomeDialogViewModel.NavEvent.CANCEL -> navController.popBackStack()
            }
        })
    }
}
