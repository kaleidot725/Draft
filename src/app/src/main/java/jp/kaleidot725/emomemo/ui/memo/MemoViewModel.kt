package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.usecase.CreateMessageUseCase
import jp.kaleidot725.emomemo.usecase.GetMessageUseCase
import jp.kaleidot725.emomemo.usecase.ObserveMessageCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveRecognizedTextUseCase
import jp.kaleidot725.emomemo.usecase.ObserveStatusUseCase
import kotlinx.coroutines.launch

class MemoViewModel(
    private val observeStatusUseCase: ObserveStatusUseCase,
    private val observeMessageCountUseCase: ObserveMessageCountUseCase,
    private val observeRecognizedTextUseCase: ObserveRecognizedTextUseCase,
    private val createMessageUseCase: CreateMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val inputMessage: MutableLiveData<String> = MutableLiveData()
    val isNotEmptyMessage: LiveData<Boolean> = inputMessage.map { it.isNotEmpty() }

    private val status: MutableLiveData<Pair<StatusEntity, Int>> = MutableLiveData()
    val messages: LiveData<PagedList<MessageEntity>> = status.switchMap { getMessageUseCase.execute(it.first.memoId) }.distinctUntilChanged()

    init {
        observeRecognizedTextUseCase.execute { recognizedMessage ->
            inputMessage.value = recognizedMessage
        }

        observeStatusUseCase.execute { newStatus ->
            observeMessageCountUseCase.dispose()
            observeMessageCountUseCase.execute(newStatus.notebookId) { count -> status.value = Pair(newStatus, count) }
        }
    }

    override fun onCleared() {
        observeRecognizedTextUseCase.dispose()
        observeStatusUseCase.dispose()
        observeMessageCountUseCase.dispose()
    }

    fun create() {
        viewModelScope.launch {
            createMessageUseCase.execute(inputMessage.value ?: "")
            inputMessage.value = ""
        }
    }
}
