package jp.kaleidot725.emomemo.ui.common.controller

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
                latestTime(getLatestTimeString(item.lastTime))
                latestMessage(getLastestMessage(item.lastMessage))
                onClickListener(View.OnClickListener { selectListener?.onSelected(item) })
            }
        }
    }

    private fun getLatestTimeString(time: Long?): String {
        return if (time != null) {
            DateFormat.format("yyyy/MM/dd hh:mm:ss", time ?: 0)
        } else {
            "0000/00/00 00:00:00"
        }.toString()
    }

    private fun getLastestMessage(message: String?): String {
        return message ?: "No Message"
    }

    interface SelectListener {
        fun onSelected(item: MemoStatusView)
    }
}
