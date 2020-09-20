package jp.kaleidot725.emomemo.ui.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TopViewModel : ViewModel() {
    private val _initialized: LiveEvent<Boolean> = LiveEvent()
    val initialized: LiveData<Boolean> = _initialized

    init {
        viewModelScope.launch {
            delay(1000)
            _initialized.value = true
        }
    }
}
