package jp.kaleidot725.emomemo.usecase.observe

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import timber.log.Timber

class ObserveMemoCountUseCase(
    private val memoRepository: MemoRepository
) {
    private var liveData: LiveData<Int>? = null
    private var observer: Observer<Int>? = null

    fun execute(notebookId: Int, block: (Int) -> Unit) {
        observer = Observer {
            try {
                block.invoke(it)
            } catch (e: Exception) {
                Timber.w(e)
            }
        }
        liveData = memoRepository.getMemoCountLiveData(notebookId)
        liveData?.observeForever(block)
    }

    fun dispose() {
        if (liveData != null && observer != null) {
            liveData!!.removeObserver(observer!!)
        }
    }
}
