package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.model.MEMO_LIST
import jp.kaleidot725.emomemo.model.Memo
import jp.kaleidot725.emomemo.ui.core.MemoItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val navController: NavController get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
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

        add_button.setOnClickListener {
            navigateHomeDialogFragment()
        }
        headerDatabindingViewController.setData(MEMO_LIST, false)
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
