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
import jp.kaleidot725.emomemo.ui.controller.MemoItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by viewBinding()
    private val navController: NavController get() = findNavController()

    private val recyclerViewController: MemoItemRecyclerViewController by lazy {
        MemoItemRecyclerViewController(requireContext(), object :
            MemoItemRecyclerViewController.SelectListener {
            override fun onSelected(item: MemoStatusView) {
                navigateMemoFragment(item)
            }
        })
    }

    private val onDestinationChangedListener: NavController.OnDestinationChangedListener =
        NavController.OnDestinationChangedListener { controller, _, _ ->
            if (controller.currentDestination?.id == R.id.homeFragment) {
                viewModel.fetchData()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        recycler_view.apply {
            this.adapter = recyclerViewController.adapter
            this.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            this.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL).apply {
                setDrawable(resources.getDrawable(R.drawable.divider, context.theme))
            })
        }

        viewModel.memoList.observe(viewLifecycleOwner, Observer {
            recyclerViewController.setData(it)
        })

        viewModel.event.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.NavEvent.ADD -> navigateHomeDialogFragment()
            }
        })

        viewModel.fetchData()
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        navController.removeOnDestinationChangedListener(onDestinationChangedListener)
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
