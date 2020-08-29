package jp.kaleidot725.emomemo.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class ObserveStatusUseCase(
    private val statusRepository: StatusRepository
) {
    private var liveData: LiveData<StatusEntity>? = null
    private var observer: Observer<StatusEntity>? = null

    fun execute(block: (StatusEntity) -> Unit) {
        observer = Observer {
            try {
                block.invoke(it ?: StatusEntity(0, 0, 0))
            } catch (e: Exception) {
                // FIXME
                Log.w("ObserveStatusUseCase", e.toString())
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
