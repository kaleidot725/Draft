package jp.kaleidot725.emomemo.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.usecase.DatabaseInitializeUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TopViewModel(
    private val databaseInitializeUsecase: DatabaseInitializeUsecase
) : ViewModel() {
    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    fun initialize() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseInitializeUsecase.execute()
            delay(1000)
            withContext(Dispatchers.Main) {
                _isCompleted.value = true
            }
        }
    }
}
