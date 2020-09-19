package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.UpdateMemoUseCase
import kotlinx.coroutines.launch

class EditMemoDialogViewModel(
    private val memo: MemoStatusView,
    private val updateMemoUseCase: UpdateMemoUseCase
) : ViewModel() {
    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    val inputTitle: MutableLiveData<String> = MutableLiveData(memo.title)
    private var inputtedTitle: String = memo.title
    private val inputTitleObserver: Observer<String> = Observer { inputtedTitle = it }

    init {
        inputTitle.observeForever(inputTitleObserver)
    }

    override fun onCleared() {
        inputTitle.removeObserver(inputTitleObserver)
    }

    fun success() {
        if (inputtedTitle.isEmpty()) {
            return
        }

        viewModelScope.launch {
            updateMemoUseCase.execute(memo.id, memo.notebookId, inputtedTitle)
            complete()
        }
    }

    fun cancel() {
        complete()
    }

    private fun complete() {
        _isCompleted.value = true
    }
}
