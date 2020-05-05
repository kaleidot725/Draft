package jp.kaleidot725.emomemo.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import jp.kaleidot725.emomemo.R
import jp.kaleidot725.emomemo.model.Memo
import jp.kaleidot725.emomemo.ui.core.MemoItemRecyclerViewController
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private val memoList = listOf(
        Memo("英語", "発音の仕方", ""),
        Memo("英語", "発音の応用", ""),
        Memo("数学", "微分・積分の仕方", ""),
        Memo("数学", "二次方程式についてのまとめ", ""),
        Memo("ただのメモ", "今日の買い物", ""),
        Memo("ただのメモ", "明日の買い物", ""),
        Memo("ごみばこ", "単語を覚えるためのメモ", ""),
        Memo("ごみばこ", "ラーメンをうまくつくるためのメモ", "")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val headerDatabindingViewController = MemoItemRecyclerViewController(object :
            MemoItemRecyclerViewController.SelectListener {
            override fun onSelected(item: String) {
                Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
            }
        })

        recycler_view.apply {
            this.adapter = headerDatabindingViewController.adapter
            this.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        headerDatabindingViewController.setData(memoList, false)
    }
}

