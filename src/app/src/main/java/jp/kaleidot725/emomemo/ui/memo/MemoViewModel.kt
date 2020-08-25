package jp.kaleidot725.emomemo.ui.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jp.kaleidot725.emomemo.model.db.datasource.MessageDataSourceFactory
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.AudioRecognizerRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.OnChangedRecognizedTextListener
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository
import jp.kaleidot725.emomemo.usecase.ObserveMessageCountUseCase
import kotlinx.coroutines.launch

class MemoViewModel(
    private val statusRepository: StatusRepository,
    private val observeMessageCountUseCase: ObserveMessageCountUseCase,
    private val audioRecognizerRepository: AudioRecognizerRepository,
    private val messageRepository: MessageRepository
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    val inputMessage: MutableLiveData<String> = MutableLiveData()
    val isNotEmptyMessage: LiveData<Boolean> = inputMessage.map { it.isNotEmpty() }
    private val onChangedRecognizedTextListener: OnChangedRecognizedTextListener = object : OnChangedRecognizedTextListener {
        override fun onChanged(text: String) {
            inputMessage.postValue(inputMessage.value + text)
        }
    }

    private val status: LiveData<StatusEntity> = statusRepository.get()
    private val statusObserver: Observer<StatusEntity> = Observer { status ->
        observeMessageCountUseCase.dispose()
        observeMessageCountUseCase.execute(status.notebookId) { count.value = it }
    }

    private val count: MutableLiveData<Int> = MutableLiveData()
    val messages: LiveData<PagedList<MessageEntity>> = count.switchMap {
        createMessagePagedListBuilder()
    }.distinctUntilChanged()

    private val selectedMemoId get() = status.value?.memoId ?: 0
    private val inputedMessage get() = inputMessage.value ?: ""

    init {
        audioRecognizerRepository.addOnChangedRecognizedTextListener(onChangedRecognizedTextListener)
        status.observeForever(statusObserver)
    }

    override fun onCleared() {
        audioRecognizerRepository.removeOnChangedRecognizedTextListener(onChangedRecognizedTextListener)
        status.removeObserver(statusObserver)
    }

    fun create() {
        viewModelScope.launch {
            messageRepository.insert(MessageEntity.create(selectedMemoId, inputedMessage))
            inputMessage.value = ""
        }
    }

    private fun createMessagePagedListBuilder(): LiveData<PagedList<MessageEntity>> {
        val factory = MessageDataSourceFactory(selectedMemoId, messageRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        return LivePagedListBuilder(factory, config).build()
    }
}
