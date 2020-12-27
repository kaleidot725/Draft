package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentHomeBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.common.controller.MemoItemRecyclerViewController
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by viewBinding()
    private val navController: NavController get() = findNavController()
    private val refreshObserver: LifecycleObserver = object : DefaultLifecycleObserver {
        override fun onResume(owner: LifecycleOwner) = viewModel.refresh()
    }

    private lateinit var epoxyController: MemoItemRecyclerViewController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeViewModel = viewModel
        binding.recyclerView.setup()
        binding.addButton.setOnClickListener { navigateHomeDialogFragment() }

        viewModel.memosWithSelectedSet.observe(viewLifecycleOwner, Observer {
            epoxyController.submitList(it.memos)
            epoxyController.submitSelectedList(it.selectedMemos)
            epoxyController.requestForcedModelBuild()
        })

        viewModel.navEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.NavEvent.NavigateMemo -> navigateMemoFragment()
                is HomeViewModel.NavEvent.NavigateMemoOption -> navigateMemoOptionFragment()
                else -> Timber.w("invalid navEvent")
            }
        })

        navController.getBackStackEntry(R.id.homeFragment).lifecycle.addObserver(refreshObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        navController.getBackStackEntry(R.id.homeFragment).lifecycle.removeObserver(refreshObserver)
    }

    private fun EpoxyRecyclerView.setup() {
        epoxyController = MemoItemRecyclerViewController(
            onClickMemo = { viewModel.tap(it) },
            onLongTapMemo = { viewModel.longTap(it) }
        )

        this.setController(epoxyController)
        this.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 100
            removeDuration = 100
            moveDuration = 100
            changeDuration = 0
        }
    }

    private fun navigateMemoFragment() {
        navController.navigate(R.id.action_homeFragment_to_memoFragment)
    }

    private fun navigateMemoOptionFragment() {
        navController.navigate(R.id.action_homeFragment_to_memoOptionDialogFragment)
    }

    private fun navigateHomeDialogFragment() {
        navController.navigate(R.id.action_homeFragment_to_homeDialogFragment)
    }
}
