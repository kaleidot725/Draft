package jp.kaleidot725.emomemo.ui.memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.model.MESSAGE_LIST
import jp.kaleidot725.emomemo.model.Message
import jp.kaleidot725.emomemo.ui.core.MessageItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.*

class MemoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_memo, container, false)
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

        messageItemRecylerViewController.setData(MESSAGE_LIST, false)
    }
}
