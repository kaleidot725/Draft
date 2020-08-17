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
import jp.kaleidot725.emomemo.ui.EmptyStatus
import jp.kaleidot725.emomemo.ui.MainViewModel
import jp.kaleidot725.emomemo.ui.common.controller.MemoItemRecyclerViewController
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
                mainViewModel.selectMemo(item)
                navigateMemoFragment()
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

        mainViewModel.memos.observe(viewLifecycleOwner, Observer {
            memoItemListController.setData(it)
        })

        mainViewModel.emptyStatus.observe(viewLifecycleOwner, Observer {
            binding.emptyMessageTextView.text = when (it) {
                EmptyStatus.NOTEBOOK -> getString(R.string.home_notebook_is_not_found)
                EmptyStatus.MEMO -> getString(R.string.home_memo_is_not_found)
                else -> ""
            }

            binding.emptyMessageTextView.visibility = when (it) {
                EmptyStatus.NOTEBOOK -> View.VISIBLE
                EmptyStatus.MEMO -> View.VISIBLE
                else -> View.GONE
            }
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
}
