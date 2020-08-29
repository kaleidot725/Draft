package jp.kaleidot725.emomemo.ui.common.controller.epoxy

import android.text.format.DateFormat
import android.view.View
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import jp.kaleidot725.emomemo.MessageItemContainerBindingModel_
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity

class MessageItemRecyclerViewController(
    private val selectListener: SelectListener? = null
) : PagedListEpoxyController<MessageEntity>() {

    override fun buildItemModel(currentPosition: Int, item: MessageEntity?): EpoxyModel<*> {
        return if (item != null) {
            MessageItemContainerBindingModel_().apply {
                id(item.time)
                time(DateFormat.format("yyyy/MM/dd aa hh:mm:ss", item.time).toString())
                title(item.value)
                onClickListener(View.OnClickListener { selectListener?.onSelected(item) })
            }
        } else {
            MessageItemContainerBindingModel_()
        }
    }
}

interface SelectListener {
    fun onSelected(item: MessageEntity)
}
