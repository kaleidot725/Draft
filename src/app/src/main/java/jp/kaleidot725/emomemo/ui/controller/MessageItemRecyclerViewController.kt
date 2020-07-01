package jp.kaleidot725.emomemo.ui.controller

import android.view.View
import com.airbnb.epoxy.Typed2EpoxyController
import jp.kaleidot725.emomemo.messageItemContainer
import jp.kaleidot725.emomemo.messageItemHeader
import jp.kaleidot725.emomemo.model.ddd.domain.Message

class MessageItemRecyclerViewController(
    private val selectListener: SelectListener? = null
) : Typed2EpoxyController<List<Message>, Boolean>() {

    override fun buildModels(memoList: List<Message>, loadingMore: Boolean) {
        memoList.groupBy { it.formattedTime }.forEach { tuple ->

            messageItemHeader {
                id(tuple.key)
                title(tuple.key)
            }

            tuple.value.forEach { message ->
                messageItemContainer {
                    id(message.toString())
                    title(message.value)
                    onClickListener(View.OnClickListener { selectListener?.onSelected(message) })
                }
            }
        }
    }

    interface SelectListener {
        fun onSelected(item: Message)
    }
}
