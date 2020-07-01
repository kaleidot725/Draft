package jp.kaleidot725.emomemo.ui.controller

import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.kaleidot725.emomemo.messageItemContainer
import jp.kaleidot725.emomemo.model.ddd.domain.Message

class MessageItemRecyclerViewController(
    private val selectListener: SelectListener? = null
) : TypedEpoxyController<List<Message>>() {

    override fun buildModels(memoList: List<Message>) {
        memoList.forEach { message ->
            messageItemContainer {
                id(message.time)
                time(message.formattedTime)
                title(message.value)
                onClickListener(View.OnClickListener { selectListener?.onSelected(message) })
            }
        }
    }
}

interface SelectListener {
    fun onSelected(item: Message)
}
