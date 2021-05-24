package jp.kaleidot725.emomemo.usecase.observe

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import timber.log.Timber

class ObserveNotebookCountUseCase(
    private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository
) {
    private var liveData: LiveData<Int>? = null
    private var observer: Observer<Int>? = null

    fun execute(block: (Int) -> Unit) {
        observer = Observer {
            try {
                block.invoke(it)
            } catch (e: Exception) {
                Timber.w(e)
            }
        }
        liveData = notebookRepository.getNotebookCountLiveData()
        liveData?.observeForever(block)
    }

    fun dispose() {
        if (liveData != null && observer != null) {
            liveData!!.removeObserver(observer!!)
        }
    }
}
