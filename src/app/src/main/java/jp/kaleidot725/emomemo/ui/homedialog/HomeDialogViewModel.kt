package jp.kaleidot725.emomemo.ui.homedialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.AppStatus
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeDialogViewModel(
    private val appStatus: AppStatus,
    private val memoRepository: MemoRepository
) : ViewModel() {
    private val _event: LiveEvent<NavEvent> = LiveEvent()
    val event: LiveData<NavEvent> = _event
    val title: MutableLiveData<String> = MutableLiveData()

    fun success() {
        if (title.value == null) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.insert(MemoEntity.create(appStatus.notebookId, title.value ?: ""))
            withContext(Dispatchers.Main) {
                _event.postValue(NavEvent.SUCCESS)
            }
        }
    }

    fun cancel() {
        _event.postValue(NavEvent.CANCEL)
    }

    enum class NavEvent {
        SUCCESS,
        CANCEL
    }
}
