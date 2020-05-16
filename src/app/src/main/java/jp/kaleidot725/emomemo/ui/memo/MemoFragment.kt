package jp.kaleidot725.emomemo.ui.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.databinding.FragmentMemoBinding
import jp.kaleidot725.emomemo.model.entity.Message
import jp.kaleidot725.emomemo.model.repository.DUMMY_MESSAGE_LIST
import jp.kaleidot725.emomemo.ui.common.inflateDB
import jp.kaleidot725.emomemo.ui.core.MessageItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MemoFragment : Fragment() {
    private val viewModel: MemoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflateDB<FragmentMemoBinding>(container, R.layout.fragment_memo, false).apply {
            this.lifecycleOwner = this@MemoFragment.viewLifecycleOwner
            this.viewModel = this@MemoFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val messageItemRecylerViewController = MessageItemRecyclerViewController(object :
            MessageItemRecyclerViewController.SelectListener {
            override fun onSelected(item: Message) {}
        })

        recycler_view.apply {
            this.adapter = messageItemRecylerViewController.adapter
            this.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        messageItemRecylerViewController.setData(DUMMY_MESSAGE_LIST, false)
    }
}
