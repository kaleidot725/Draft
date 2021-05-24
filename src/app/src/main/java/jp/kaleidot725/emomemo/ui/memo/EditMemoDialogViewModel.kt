package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.data.view.MemoStatusView
import jp.kaleidot725.emomemo.domain.usecase.select.GetSelectedMemoUseCase
import jp.kaleidot725.emomemo.domain.usecase.select.UpdateSelectedMemoUseCase
import kotlinx.coroutines.launch

class EditMemoDialogViewModel(
    private val getSelectedMemoUseCase: GetSelectedMemoUseCase,
    private val updateSelectedMemoUseCase: UpdateSelectedMemoUseCase
) : ViewModel() {
    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    private val _memo: MutableLiveData<MemoStatusView> = MutableLiveData()
    val memo: LiveData<MemoStatusView> = _memo

    val inputTitle: MutableLiveData<String> = MutableLiveData()
    private var inputtedTitle: String = ""
    private val inputTitleObserver: Observer<String> = Observer { inputtedTitle = it }

    init {
        viewModelScope.launch {
            getSelectedMemoUseCase.execute()?.let { memo ->
                inputTitle.value = memo.title
                inputTitle.observeForever(inputTitleObserver)
            }
        }
    }

    override fun onCleared() {
        inputTitle.removeObserver(inputTitleObserver)
    }

    fun success() {
        if (inputtedTitle.isEmpty()) {
            return
        }

        viewModelScope.launch {
            updateSelectedMemoUseCase.execute(inputtedTitle)
            _isCompleted.value = true
        }
    }

    fun cancel() {
        _isCompleted.value = true
    }
}
