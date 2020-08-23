package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
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
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.ui.MainViewModel
import jp.kaleidot725.emomemo.ui.common.controller.MemoItemRecyclerViewController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by viewBinding()
    private val navController: NavController get() = findNavController()
    private lateinit var epoxyController: MemoItemRecyclerViewController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainViewModel = mainViewModel
        binding.homeViewModel = homeViewModel

        binding.recyclerView.setup()
        binding.addButton.setOnClickListener { navigateHomeDialogFragment() }

        mainViewModel.memos.observe(viewLifecycleOwner, Observer {
            epoxyController.submitList(it)
            epoxyController.requestModelBuild()
        })

        mainViewModel.selectedNotebook.observe(viewLifecycleOwner, Observer {
            requireActivity().title = it.title
        })
    }

    private fun navigateMemoFragment() {
        navController.navigate(R.id.action_homeFragment_to_memoFragment)
    }

    private fun navigateHomeDialogFragment() {
        navController.navigate(R.id.action_homeFragment_to_homeDialogFragment)
    }

    private fun EpoxyRecyclerView.setup() {
        epoxyController = MemoItemRecyclerViewController(object : MemoItemRecyclerViewController.SelectListener {
            override fun onSelected(item: MemoStatusView) {
                mainViewModel.selectMemo(item)
                navigateMemoFragment()
            }
        })
        this.setController(epoxyController)

        val drawable = resources.getDrawable(R.drawable.divider, requireContext().theme)
        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply { setDrawable(drawable) }
        this.addItemDecoration(decoration)
    }
}
