package jp.kaleidot725.emomemo.ui.core

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.kaleidot725.emomemo.messageItemContainer
import jp.kaleidot725.emomemo.messageItemHeader
import jp.kaleidot725.emomemo.model.entity.Message

class MessageItemRecyclerViewController(
    private val selectListener: SelectListener
) : Typed2EpoxyController<List<Message>, Boolean>() {

    override fun buildModels(memoList: List<Message>, loadingMore: Boolean) {
        memoList.groupBy { it.time }.forEach {
            messageItemHeader {
                id("Header")
                title(it.key.toString())
            }

            it.value.forEach { item ->
                messageItemContainer {
                    id("Content")
                    title(item.value)
                    onClickListener(View.OnClickListener { selectListener.onSelected(item) })
                }
            }
        }
    }

    interface SelectListener {
        fun onSelected(item: Message)
    }
}
