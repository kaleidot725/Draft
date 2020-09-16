package jp.kaleidot725.emomemo.ui.common.controller

import android.text.format.DateFormat
import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import jp.kaleidot725.emomemo.MessageItemContainerBindingModel_
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

typealias OnClickMessage = (item: MessageEntity) -> Unit
typealias OnLongTapMessage = (item: MessageEntity) -> Unit

class MessageItemRecyclerViewController(
    private val onClickMessage: OnClickMessage? = null,
    private val onLongTapMessage: OnLongTapMessage? = null
) : PagedListEpoxyController<MessageEntity>() {
    private var selected: List<MessageEntity> = mutableListOf()

    override fun buildItemModel(currentPosition: Int, item: MessageEntity?): EpoxyModel<*> {
        return if (item != null) {
            MessageItemContainerBindingModel_().apply {
                id(item.time)
                time(DateFormat.format("yyyy/MM/dd aa hh:mm:ss", item.time).toString())
                title(item.value)
                selected(selected.contains(item))
                onClickListener(View.OnClickListener {
                    onClickMessage?.invoke(item)
                })
                onLongClickListener(View.OnLongClickListener {
                    onLongTapMessage?.invoke(item)
                    true
                })
            }
        } else {
            MessageItemContainerBindingModel_()
        }
    }

    fun submitSelectedList(selected: List<MessageEntity>) {
        this.selected = selected
    }
}
