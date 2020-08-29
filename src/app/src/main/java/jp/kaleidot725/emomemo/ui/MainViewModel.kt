package jp.kaleidot725.emomemo.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.datasource.MemoStatusDataNullSourceFactory
import jp.kaleidot725.emomemo.model.db.datasource.MemoStatusDataSourceFactory
import jp.kaleidot725.emomemo.model.db.datasource.MessageDataNullSourceFactory
import jp.kaleidot725.emomemo.model.db.datasource.MessageDataSourceFactory
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.InitializeDataBaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val memoRepository: MemoRepository,
    private val memoStatusRepository: MemoStatusRepository,
    private val messageRepository: MessageRepository,
    private val notebookRepository: NotebookRepository,
    private val statusRepository: StatusRepository,
    private val initializeDataBaseUseCase: InitializeDataBaseUseCase
) : ViewModel() {
    private val _initialized: LiveEvent<Boolean> = LiveEvent()
    val initialized: LiveData<Boolean> = _initialized

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val status: LiveData<StatusEntity> = statusRepository.getLiveData()
    private var selectedNotebookId = ERROR_NOTEBOOK.id
    private var selectedMemoId = ERROR_MEMO.id

    val selectedNotebook: LiveData<NotebookEntity> = status.switchMap {
        liveData(viewModelScope.coroutineContext) {
            try {
                emit(notebookRepository.getNoteBook(it.notebookId))
            } catch (e: Exception) {
                emit(ERROR_NOTEBOOK)
            }
        }
    }

    val selectedMemo: LiveData<MemoStatusView> = status.switchMap {
        liveData {
            try {
                emit(memoStatusRepository.getMemoByMemoId(it.memoId))
            } catch (e: Exception) {
                emit(ERROR_MEMO)
            }
        }
    }

    val notebooks: LiveData<List<NotebookEntity>> = notebookRepository.getAllLiveData()

    private val refreshMemos: MutableLiveData<Unit> = MutableLiveData()
    val memos: LiveData<PagedList<MemoStatusView>> = refreshMemos.switchMap {
        val factory = if (selectedNotebookId == ERROR_NOTEBOOK.id) {
            MemoStatusDataNullSourceFactory()
        } else {
            MemoStatusDataSourceFactory(selectedNotebookId, memoStatusRepository)
        }
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        LivePagedListBuilder(factory, config).build()
    }.distinctUntilChanged()

    private val refreshMessages: MutableLiveData<Unit> = MutableLiveData()
    val messages: LiveData<PagedList<MessageEntity>?> = refreshMessages.switchMap {
        val factory = if (selectedMemoId == ERROR_NOTEBOOK.id) {
            MessageDataNullSourceFactory()
        } else {
            MessageDataSourceFactory(selectedMemoId, messageRepository)
        }

        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        LivePagedListBuilder(factory, config).build()
    }.distinctUntilChanged()

    init {
        viewModelScope.launch {
            initializeDataBaseUseCase.execute()
            status.observeForever { refresh() }
            refresh()
            _initialized.value = true
        }
    }

    fun createNotebook(title: String) {
        viewModelScope.launch {
            notebookRepository.insert(NotebookEntity.create(title))
            refresh()
        }
    }

    fun selectNotebook(notebook: NotebookEntity) {
        viewModelScope.launch {
            statusRepository.update(notebook.id, ERROR_MEMO.id)
        }
    }

    fun deleteNotebook(notebook: NotebookEntity) {
        viewModelScope.launch {
            notebookRepository.delete(notebook)
            if (notebook.id == status.value?.notebookId) {
                statusRepository.update(ERROR_NOTEBOOK.id, ERROR_MEMO.id)
            }
        }
    }

    fun createMemo(title: String) {
        viewModelScope.launch {
            memoRepository.insert(MemoEntity.create(selectedNotebookId, title))
            refresh()
        }
    }

    fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.delete(memo)
            if (memo.id == status.value?.memoId) {
                statusRepository.update(selectedNotebookId, ERROR_MEMO.id)
            }
        }
    }

    fun createMessage(message: String) {
        viewModelScope.launch {
            messageRepository.insert(MessageEntity.create(selectedMemoId, message))
            refresh()
        }
    }

    fun deleteMessage(message: MessageEntity) {
        viewModelScope.launch {
            messageRepository.delete(message)
            refreshMemos.value = Unit
        }
    }

    private fun refresh() {
        selectedNotebookId = status.value?.notebookId ?: ERROR_NOTEBOOK.id
        selectedMemoId = status.value?.memoId ?: ERROR_MEMO.id
        Log.v("TAG", "observe note $selectedNotebookId memo $selectedMemoId")

        refreshMemos.postValue(Unit)
        refreshMessages.postValue(Unit)
    }

    companion object {
        private val ERROR_NOTEBOOK = NotebookEntity.create("")
        private val ERROR_MEMO = MemoStatusView(0, 0, "", 0, 0, "")
    }
}
