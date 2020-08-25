package jp.kaleidot725.emomemo.usecase

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
        observer = Observer { block.invoke(it ?: StatusEntity(0, 0, 0)) }
        liveData = statusRepository.getLiveData().distinctUntilChanged()
        liveData?.observeForever(block)
    }

    fun dispose() {
        if (liveData != null && observer != null) {
            liveData!!.removeObserver(observer!!)
        }
    }
}
