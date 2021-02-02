package jp.kaleidot725.emomemo.ui.notebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.usecase.update.UpdateNotebookUseCase
import kotlinx.coroutines.launch

class EditNotebookDialogViewModel(
    private val notebookEntity: NotebookEntity,
    private val updateNotebookUseCase: UpdateNotebookUseCase
) : ViewModel() {
    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    val inputTitle: MutableLiveData<String> = MutableLiveData(notebookEntity.title)
    private var inputtedTitle: String = notebookEntity.title
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
            updateNotebookUseCase.execute(notebookEntity, inputtedTitle)
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
