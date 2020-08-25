package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class ObserveMessageCountUseCase(
    private val messageRepository: MessageRepository
) {
    private var liveData: LiveData<Int>? = null
    private var observer: Observer<Int>? = null

    fun execute(memoId: Int, block: (Int) -> Unit) {
        observer = Observer { block.invoke(it ?: 0) }
        liveData = messageRepository.getMessageCount(memoId)
        liveData?.observeForever(block)
    }

    fun dispose() {
        if (liveData != null && observer != null) {
            liveData!!.removeObserver(observer!!)
        }
    }
}
