package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import timber.log.Timber

class ObserveNotebookCountUseCase(
    private val notebookRepository: NotebookRepository
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
