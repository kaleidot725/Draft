package jp.kaleidot725.emomemo.ui.core

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.kaleidot725.emomemo.memoItemContainer
import jp.kaleidot725.emomemo.memoItemHeader
import jp.kaleidot725.emomemo.model.entity.Memo

class MemoItemRecyclerViewController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<Memo>, Boolean>() {

    override fun buildModels(memoList: List<Memo>, loadingMore: Boolean) {
        memoList.groupBy { it.tag }.forEach {
            memoItemHeader {
                id("Header")
                title(it.key)
            }

            it.value.forEach { item ->
                memoItemContainer {
                    id("Content")
                    title(item.title)
                    onClickListener(View.OnClickListener { selectListener.onSelected(item) })
                }
            }
        }
    }

    interface SelectListener {
        fun onSelected(item: Memo)
    }
}
