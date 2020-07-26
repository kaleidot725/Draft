package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentHomeBinding
import jp.kaleidot725.emomemo.extension.viewBinding
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.ui.MainViewModel
import jp.kaleidot725.emomemo.ui.controller.MemoItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.recycler_view
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by viewBinding()
    private val navController: NavController get() = findNavController()
    private lateinit var memoItemListController: MemoItemRecyclerViewController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        memoItemListController = MemoItemRecyclerViewController(object : MemoItemRecyclerViewController.SelectListener {
            override fun onSelected(item: MemoStatusView) {
                mainViewModel.selectMemo(item.id)
                navigateMemoFragment(item)
            }
        })

        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decoration.setDrawable(resources.getDrawable(R.drawable.divider, requireContext().theme))
        recycler_view.addItemDecoration(decoration)
        recycler_view.adapter = memoItemListController.adapter

        binding.mainViewModel = mainViewModel
        binding.homeViewModel = homeViewModel
        binding.addButton.setOnClickListener {
            navigateHomeDialogFragment()
        }

        mainViewModel.memoStatusList.observe(viewLifecycleOwner, Observer {
            memoItemListController.setData(it)
        })
    }

    private fun navigateMemoFragment(memo: MemoStatusView) {
        val action = HomeFragmentDirections.actionHomeFragmentToMemoFragment(memo.id.toLong(), memo.title)
        navController.navigate(action)
    }

    private fun navigateHomeDialogFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToHomeDialogFragment()
        navController.navigate(action)
    }
}
