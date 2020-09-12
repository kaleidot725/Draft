package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentHomeBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.ui.common.controller.ActionModeController
import jp.kaleidot725.emomemo.ui.common.controller.MemoItemRecyclerViewController
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by viewBinding()
    private val navController: NavController get() = findNavController()

    private lateinit var epoxyController: MemoItemRecyclerViewController
    private lateinit var actionModeController: ActionModeController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeViewModel = viewModel
        binding.recyclerView.setup()
        binding.addButton.setOnClickListener { navigateHomeDialogFragment() }

        viewModel.memos.observe(viewLifecycleOwner, Observer {
            epoxyController.submitList(it)
            epoxyController.requestModelBuild()
        })

        viewModel.selected.observe(viewLifecycleOwner, Observer {
            epoxyController.submitSelectedList(it.toList())
            epoxyController.requestForcedModelBuild()
        })

        viewModel.actionEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.ActionEvent.ON -> actionModeController.startActionMode(requireActivity())
                HomeViewModel.ActionEvent.OFF -> actionModeController.cancelActionMode()
                else -> Log.w("HomeFragment", "invalid actionEvent")
            }
        })

        viewModel.navEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.NavEvent.NAVIGATE_MEMO -> navigateMemoFragment()
                else -> Log.w("HomeFragment", "invalid navEvent")
            }
        })
    }

    private fun EpoxyRecyclerView.setup() {
        actionModeController = ActionModeController(
            R.menu.memo_action_menu,
            ActionMode.TYPE_PRIMARY,
            onAction = { viewModel.deleteAction() },
            onDestroy = { viewModel.cancelAction() }
        )

        epoxyController = MemoItemRecyclerViewController(
            onClickMemo = { viewModel.select(it) },
            onLongTapMemo = { viewModel.startAction(it) }
        )

        this.setController(epoxyController)

        val drawable = resources.getDrawable(R.drawable.divider, requireContext().theme)
        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply { setDrawable(drawable) }
        this.addItemDecoration(decoration)
    }

    private fun navigateMemoFragment() {
        navController.navigate(R.id.action_homeFragment_to_memoFragment)
    }

    private fun navigateHomeDialogFragment() {
        navController.navigate(R.id.action_homeFragment_to_homeDialogFragment)
    }
}
