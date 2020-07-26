package jp.kaleidot725.emomemo.ui.top

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentTopBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TopFragment : Fragment(R.layout.fragment_top) {
    private val navController: NavController get() = findNavController()
    private val viewModel: MainViewModel by sharedViewModel()
    private val binding: FragmentTopBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup DataBinding
        binding.viewModel = viewModel

        // Setup View Model
        viewModel.isCompleted.observe(viewLifecycleOwner, Observer { isCompleted ->
            if (isCompleted) {
                runBlocking { delay(1000L) }
                navController.navigate(R.id.action_topFragment_to_homeFragment)
            }
        })
    }
}
