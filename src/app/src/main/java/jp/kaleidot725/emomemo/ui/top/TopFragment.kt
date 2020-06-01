package jp.kaleidot725.emomemo.ui.top

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentTopBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopFragment : Fragment(R.layout.fragment_top) {
    private val navController: NavController get() = findNavController()
    private val viewModel: TopViewModel by viewModel()
    private val binding: FragmentTopBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        view.postDelayed(Runnable {
            navController.navigate(R.id.action_topFragment_to_homeFragment)
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 3000L
    }
}
