package jp.kaleidot725.emomemo.ui.controller

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.kaleidot725.emomemo.memoItemContainer
import jp.kaleidot725.emomemo.model.ddd.domain.Memo

class MemoItemRecyclerViewController(
    private val selectListener: SelectListener? = null
) : TypedEpoxyController<List<Memo>>() {

    override fun buildModels(memoList: List<Memo>) {
        memoList.forEach { item ->
            memoItemContainer {
                id(item.id)
                title(item.title)
                onClickListener(View.OnClickListener { selectListener?.onSelected(item) })
            }
        }
    }

    interface SelectListener {
        fun onSelected(item: Memo)
    }
}
