package jp.kaleidot725.emomemo.ui.common.controller

import android.text.format.DateFormat
import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import jp.kaleidot725.emomemo.MessageItemContainerBindingModel_
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

typealias OnLongTapMessage = (item: MessageEntity) -> Unit

class MessageItemRecyclerViewController(
    private val OnLongTapMessage: OnLongTapMessage? = null
) : PagedListEpoxyController<MessageEntity>() {

    override fun buildItemModel(currentPosition: Int, item: MessageEntity?): EpoxyModel<*> {
        return if (item != null) {
            MessageItemContainerBindingModel_().apply {
                id(item.time)
                time(DateFormat.format("yyyy/MM/dd aa hh:mm:ss", item.time).toString())
                title(item.value)
                onLongClickListener(View.OnLongClickListener {
                    OnLongTapMessage?.invoke(item)
                    true
                })
            }
        } else {
            MessageItemContainerBindingModel_()
        }
    }
}

