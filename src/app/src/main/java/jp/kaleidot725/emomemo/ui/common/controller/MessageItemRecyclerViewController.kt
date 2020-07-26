package jp.kaleidot725.emomemo.ui.common.controller

import android.text.format.DateFormat
import android.view.View
import com.airbnb.epoxy.TypedEpoxyController
import jp.kaleidot725.emomemo.messageItemContainer
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

class MessageItemRecyclerViewController(
    private val selectListener: SelectListener? = null
) : TypedEpoxyController<List<MessageEntity>>() {

    override fun buildModels(memoList: List<MessageEntity>) {
        memoList.forEach { message ->
            messageItemContainer {
                id(message.time)
                time(DateFormat.format("yyyy/MM/dd hh:mm:ss", message.time).toString())
                title(message.value)
                onClickListener(View.OnClickListener { selectListener?.onSelected(message) })
            }
        }
    }
}

interface SelectListener {
    fun onSelected(item: MessageEntity)
}
