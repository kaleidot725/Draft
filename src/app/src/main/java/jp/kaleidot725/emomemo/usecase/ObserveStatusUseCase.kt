package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository
import timber.log.Timber

class ObserveStatusUseCase(
    private val statusRepository: StatusRepository
) {
    private var liveData: LiveData<StatusEntity?>? = null
    private var observer: Observer<StatusEntity?>? = null

    fun execute(block: (StatusEntity?) -> Unit) {
        observer = Observer {
            try {
                block.invoke(it ?: StatusEntity())
            } catch (e: Exception) {
                Timber.w(e)
            }
        }
        liveData = statusRepository.getLiveData().distinctUntilChanged()
        liveData?.observeForever(block)
    }

    fun dispose() {
        if (liveData != null && observer != null) {
            liveData!!.removeObserver(observer!!)
        }
    }
}
