package jp.kaleidot725.emomemo.ui.common.controller

import android.text.format.DateFormat
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import jp.kaleidot725.emomemo.MemoItemContainerBindingModel_
import kotlinx.android.synthetic.main.memo_item_container.view.container

typealias OnClickMemo = (item: jp.kaleidot725.emomemo.data.view.MemoStatusView) -> Unit
typealias OnLongTapMemo = (item: jp.kaleidot725.emomemo.data.view.MemoStatusView) -> Unit

class MemoItemRecyclerViewController(
    private val onClickMemo: OnClickMemo? = null,
    private val onLongTapMemo: OnLongTapMemo? = null
) : PagedListEpoxyController<jp.kaleidot725.emomemo.data.view.MemoStatusView>() {
    override fun buildItemModel(currentPosition: Int, item: jp.kaleidot725.emomemo.data.view.MemoStatusView?): EpoxyModel<*> {
        return if (item != null) {
            MemoItemContainerBindingModel_().apply {
                id(item.id)
                title(item.title)
                detail(getDetailString(item))
                onBind { _, view, _ ->
                    view.dataBinding.root.container.apply {
                        setOnClickListener {
                            onClickMemo?.invoke(item)
                        }
                        setOnLongClickListener {
                            onLongTapMemo?.invoke(item)
                            true
                        }
                    }
                }
            }
        } else {
            MemoItemContainerBindingModel_()
        }
    }

    private fun getDetailString(memoStatusView: jp.kaleidot725.emomemo.data.view.MemoStatusView): String {
        return getLatestTimeString(memoStatusView.lastTime) + getLatestMessage(memoStatusView.lastMessage)
    }

    private fun getLatestTimeString(time: Long?): String {
        return if (time != null) DateFormat.format("yyyy/MM/dd aa hh:mm:ss", time).toString() + "\n" else ""
    }

    private fun getLatestMessage(message: String?): String {
        return message ?: "No Message"
    }
}
