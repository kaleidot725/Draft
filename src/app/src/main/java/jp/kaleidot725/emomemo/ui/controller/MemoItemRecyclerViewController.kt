package jp.kaleidot725.emomemo.ui.controller

import android.text.format.DateFormat
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.kaleidot725.emomemo.memoItemContainer
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoItemRecyclerViewController(
    private val selectListener: SelectListener? = null
) : TypedEpoxyController<List<MemoStatusView>>() {

    override fun buildModels(memoList: List<MemoStatusView>) {
        memoList.forEach { item ->
            memoItemContainer {
                id(item.id)
                title(item.title)
                latestTime(DateFormat.format("yyyy/MM/dd hh:mm:ss", item.lastTime).toString())
                latestMessage(item.lastMessage)
                onClickListener(View.OnClickListener { selectListener?.onSelected(item) })
            }
        }
    }

    interface SelectListener {
        fun onSelected(item: MemoStatusView)
    }
}
