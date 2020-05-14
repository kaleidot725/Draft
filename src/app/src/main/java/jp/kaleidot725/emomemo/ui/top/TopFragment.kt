package jp.kaleidot725.emomemo.ui.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentTopBinding
import jp.kaleidot725.emomemo.ui.common.inflateDB
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopFragment : Fragment() {
    private val navController: NavController get() = findNavController()
    private val viewModel: TopViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateDB<FragmentTopBinding>(container, R.layout.fragment_top, false).apply {
            this.lifecycleOwner = this@TopFragment.viewLifecycleOwner
            this.viewModel = this@TopFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed(Runnable {
            navController.navigate(R.id.action_topFragment_to_homeFragment)
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 3000L
    }
}
