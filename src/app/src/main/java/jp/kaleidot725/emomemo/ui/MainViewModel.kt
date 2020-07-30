package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import jp.kaleidot725.emomemo.model.db.entity.MemoEntity
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.DatabaseInitializeUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val memoRepository: MemoRepository,
    private val memoStatusRepository: MemoStatusRepository,
    private val messageRepository: MessageRepository,
    private val notebookRepository: NotebookRepository,
    private val databaseInitializeUsecase: DatabaseInitializeUsecase
) : ViewModel() {
    private var memoId: Int = UNKNOWN_MEMO_ID
    private var noteBookId: Int = UNKNOWN_NOTEBOOK_ID
    private val refresh: MutableLiveData<Unit> = MutableLiveData()

    private val _initialized: LiveEvent<Boolean> = LiveEvent()
    val initialized: LiveData<Boolean> = _initialized

    val selectedNotebook: LiveData<NotebookEntity> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            val notebook = if (noteBookId != UNKNOWN_NOTEBOOK_ID) {
                notebookRepository.getNoteBook(noteBookId)
            } else {
                ERROR_NOTEBOOK
            }
            emit(notebook)
        }
    }

    val selectedMemo: LiveData<MemoStatusView> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            val memo = if (memoId != UNKNOWN_MEMO_ID) {
                memoStatusRepository.getMemo(memoId)
            } else {
                ERROR_MEMO
            }
            emit(memo)
        }
    }

    val notebooks: LiveData<List<NotebookEntity>> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(notebookRepository.getAll())
        }
    }

    val memos: LiveData<List<MemoStatusView>> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(memoStatusRepository.getAll().filter { it.notebookId == noteBookId })
        }
    }

    val messages: LiveData<List<MessageEntity>> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(messageRepository.getAll().filter { it.memoId == memoId })
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            databaseInitializeUsecase.execute()
            initializeSelectedNotebook()

            withContext(Dispatchers.Main) {
                _initialized.value = true
                refresh.value = Unit
            }
        }
    }

    val emptyStatus: LiveData<EmptyStatus> = MediatorLiveData<EmptyStatus>().apply {
        fun getEmptyStatus() {
            if (notebooks.value.isNullOrEmpty()) {
                this.value = EmptyStatus.NOTEBOOK
                return
            }
            if (memos.value.isNullOrEmpty()) {
                this.value = EmptyStatus.MEMO
                return
            }
            if (messages.value.isNullOrEmpty()) {
                this.value = EmptyStatus.MESSAGE
                return
            }

            this.value = EmptyStatus.NO_ERROR
            return
        }

        addSource(notebooks) { getEmptyStatus() }
        addSource(memos) { getEmptyStatus() }
    }

    fun createNotebook(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notebookRepository.insert(NotebookEntity.create(title))
            if (noteBookId == UNKNOWN_NOTEBOOK_ID) {
                initializeSelectedNotebook()
            }
            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    fun selectNotebook(id: Int) {
        noteBookId = id
        refresh.value = Unit
    }

    fun deleteNotebook(notebook: NotebookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            notebookRepository.delete(notebook)
            if (noteBookId == notebook.id) {
                initializeSelectedNotebook()
            }
            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    fun createMemo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.insert(MemoEntity.create(noteBookId, title))
            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    fun selectMemo(id: Int) {
        memoId = id
        refresh.value = Unit
    }

    fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.delete(memo)
            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    fun createMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.insert(MessageEntity.create(memoId, message))
            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    fun deleteMessage(message: MessageEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.delete(message)
            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    private suspend fun initializeSelectedNotebook() {
        this@MainViewModel.noteBookId = notebookRepository.getAll().firstOrNull()?.id ?: UNKNOWN_NOTEBOOK_ID
        this@MainViewModel.memoId = memoStatusRepository.getAll().firstOrNull { it.notebookId == noteBookId }?.id ?: UNKNOWN_MEMO_ID
    }

    companion object {
        private const val UNKNOWN_MEMO_ID = -1
        private const val UNKNOWN_NOTEBOOK_ID = -1
        private val ERROR_NOTEBOOK = NotebookEntity.create("")
        private val ERROR_MEMO = MemoStatusView(0, UNKNOWN_NOTEBOOK_ID, "", 0, 0, "")
    }
}
