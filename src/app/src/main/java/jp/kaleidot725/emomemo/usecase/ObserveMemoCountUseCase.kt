package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository

class ObserveMemoCountUseCase(
    private val memoRepository: MemoRepository
) {
    private var liveData: LiveData<Int>? = null
    private var observer: Observer<Int>? = null

    fun execute(notebookId: Int, block: (Int) -> Unit) {
        observer = Observer<Int> { block.invoke(it ?: 0) }
        liveData = memoRepository.getMemoCount(notebookId)
        liveData?.observeForever(block)
    }

    fun dispose() {
        if (liveData != null && observer != null) {
            liveData!!.removeObserver(observer!!)
        }
    }
}
