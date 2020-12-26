package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.GetMemoUseCase
import jp.kaleidot725.emomemo.usecase.GetStatusUseCase
import jp.kaleidot725.emomemo.usecase.UpdateMemoUseCase
import kotlinx.coroutines.launch

class EditMemoDialogViewModel(
    private val getStatusUseCase: GetStatusUseCase,
    private val getMemoUseCase: GetMemoUseCase,
    private val updateMemoUseCase: UpdateMemoUseCase
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
            val status = getStatusUseCase.execute()
            val memo = getMemoUseCase.execute(status.memoId) ?: return@launch
            inputTitle.value = memo.title
            inputTitle.observeForever(inputTitleObserver)
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
            val status = getStatusUseCase.execute()
            val memo = getMemoUseCase.execute(status.memoId) ?: return@launch
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
