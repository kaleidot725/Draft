package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
import android.view.ActionMode
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
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.ui.common.ActionModeEvent
import jp.kaleidot725.emomemo.ui.common.controller.ActionModeController
import jp.kaleidot725.emomemo.ui.common.controller.MemoItemRecyclerViewController
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
    private lateinit var actionModeController: ActionModeController

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

        viewModel.actionMode.observe(viewLifecycleOwner, Observer {
            when (it) {
                ActionModeEvent.ON -> actionModeController.startActionMode(requireActivity())
                ActionModeEvent.OFF -> actionModeController.cancelActionMode()
                else -> Timber.w("invalid actionEvent")
            }
        })

        viewModel.navEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.NavEvent.NavigateMemo -> navigateMemoFragment()
                is HomeViewModel.NavEvent.EditMemo -> navigateEditMemoFragment(it.memo)
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
        actionModeController = ActionModeController(
            R.menu.memo_action_menu,
            ActionMode.TYPE_PRIMARY,
            onAction = {
                when (it.itemId) {
                    R.id.delete -> viewModel.deleteAction()
                    R.id.edit -> viewModel.editAction()
                }
            },
            onDestroy = { viewModel.cancelAction() }
        )

        epoxyController = MemoItemRecyclerViewController(
            onClickMemo = { viewModel.select(it) },
            onLongTapMemo = { viewModel.startAction(it) }
        )

        this.setController(epoxyController)
    }

    private fun navigateMemoFragment() {
        navController.navigate(R.id.action_homeFragment_to_memoFragment)
    }

    private fun navigateEditMemoFragment(memo: MemoStatusView) {
        val action = HomeFragmentDirections.actionHomeFragmentToEditMemoDialogFragment(memo)
        navController.navigate(action)
    }

    private fun navigateHomeDialogFragment() {
        navController.navigate(R.id.action_homeFragment_to_homeDialogFragment)
    }
}
