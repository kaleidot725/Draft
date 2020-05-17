package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentHomeBinding
import jp.kaleidot725.emomemo.model.entity.Memo
import jp.kaleidot725.emomemo.ui.common.inflateDB
import jp.kaleidot725.emomemo.ui.core.MemoItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val navController: NavController get() = findNavController()
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateDB<FragmentHomeBinding>(container, R.layout.fragment_home, false).apply {
            this.lifecycleOwner = this@HomeFragment.viewLifecycleOwner
            this.viewModel = this@HomeFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headerDatabindingViewController = MemoItemRecyclerViewController(object :
            MemoItemRecyclerViewController.SelectListener {
            override fun onSelected(item: Memo) {
                navigateMemoFragment(item)
            }
        })

        recycler_view.apply {
            this.adapter = headerDatabindingViewController.adapter
            this.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        viewModel.memoList.observe(viewLifecycleOwner, Observer {
            headerDatabindingViewController.setData(it, false)
        })

        viewModel.event.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.NavEvent.ADD -> navigateHomeDialogFragment()
            }
        })
    }

    private fun navigateMemoFragment(memo: Memo) {
        val action = HomeFragmentDirections.actionHomeFragmentToMemoFragment(memo.id, memo.title)
        navController.navigate(action)
    }

    private fun navigateHomeDialogFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToHomeDialogFragment()
        navController.navigate(action)
    }
}
