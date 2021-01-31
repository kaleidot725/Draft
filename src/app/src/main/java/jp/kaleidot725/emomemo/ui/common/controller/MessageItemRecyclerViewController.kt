package jp.kaleidot725.emomemo.ui.common.controller

import android.text.format.DateFormat
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import jp.kaleidot725.emomemo.MessageItemContainerBindingModel_
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import kotlinx.android.synthetic.main.message_item_container.view.container

typealias OnClickMessage = (item: MessageEntity) -> Unit
typealias OnLongTapMessage = (item: MessageEntity) -> Unit

class MessageItemRecyclerViewController(
    private val onClickMessage: OnClickMessage? = null,
    private val onLongTapMessage: OnLongTapMessage? = null
) : PagedListEpoxyController<MessageEntity>() {
    override fun buildItemModel(currentPosition: Int, item: MessageEntity?): EpoxyModel<*> {
        return if (item != null) {
            MessageItemContainerBindingModel_().apply {
                id(item.time)
                time(DateFormat.format("yyyy/MM/dd aa hh:mm:ss", item.time).toString())
                title(item.value)
                onBind { _, view, _ ->
                    view.dataBinding.root.container.apply {
                        setOnClickListener {
                            onClickMessage?.invoke(item)
                        }
                        setOnLongClickListener {
                            onLongTapMessage?.invoke(item)
                            true
                        }
                    }
                }
            }
        } else {
            MessageItemContainerBindingModel_()
        }
    }
}
