package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val _initialized: LiveEvent<Boolean> = LiveEvent()
    val initialized: LiveData<Boolean> = _initialized

    private val _selectedNotebook: MutableLiveData<NotebookEntity> = MutableLiveData()
    val selectedNotebook: LiveData<NotebookEntity> = _selectedNotebook

    private val _selectedMemo: MutableLiveData<MemoStatusView> = MutableLiveData()
    val selectedMemo: LiveData<MemoStatusView> = _selectedMemo

    private val _notebooks: MutableLiveData<List<NotebookEntity>> = MutableLiveData()
    val notebooks: LiveData<List<NotebookEntity>> = _notebooks

    private val _memos: MutableLiveData<List<MemoStatusView>> = MutableLiveData()
    val memos: LiveData<List<MemoStatusView>> = _memos

    private val _messages: MutableLiveData<List<MessageEntity>> = MutableLiveData()
    val messages: LiveData<List<MessageEntity>> = _messages

    init {
        viewModelScope.launch(Dispatchers.IO) {
            databaseInitializeUsecase.execute()
            fetchData(true)

            withContext(Dispatchers.Main) {
                _initialized.value = true
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
        addSource(messages) { getEmptyStatus() }
    }

    fun createNotebook(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notebookRepository.insert(NotebookEntity.create(title))
            fetchData(false)
        }
    }

    fun selectNotebook(id: Int) {
        _notebooks.value = emptyList()
        _selectedNotebook.value = ERROR_NOTEBOOK
        _memos.value = emptyList()
        _selectedMemo.value = ERROR_MEMO
        _messages.value = emptyList()
        viewModelScope.launch(Dispatchers.IO) {
            noteBookId = id
            fetchData(false)
        }
    }

    fun deleteNotebook(notebook: NotebookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            notebookRepository.delete(notebook)
            fetchData(noteBookId == notebook.id)
        }
    }

    fun createMemo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.insert(MemoEntity.create(noteBookId, title))
            fetchData(false)
        }
    }

    fun selectMemo(id: Int) {
        _messages.value = emptyList()
        _selectedMemo.value = ERROR_MEMO
        viewModelScope.launch(Dispatchers.IO) {
            memoId = id
            fetchData(false)
        }
    }

    fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.delete(memo)
            fetchData(memoId == memo.id)
        }
    }

    fun createMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.insert(MessageEntity.create(memoId, message))
            fetchData(false)
        }
    }

    fun deleteMessage(message: MessageEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.delete(message)
            fetchData(false)
        }
    }

    private suspend fun fetchData(reselect: Boolean) {
        if (reselect) {
            this@MainViewModel.noteBookId = notebookRepository.getAll().firstOrNull()?.id ?: UNKNOWN_NOTEBOOK_ID
            this@MainViewModel.memoId = memoStatusRepository.getAll().firstOrNull { it.notebookId == noteBookId }?.id ?: UNKNOWN_MEMO_ID
        }

        val selectedNotebook = try {
            notebookRepository.getNoteBook(noteBookId)
        } catch (e: Exception) {
            ERROR_NOTEBOOK
        }

        val selectedMemo = try {
            memoStatusRepository.getMemo(memoId)
        } catch (e: Exception) {
            ERROR_MEMO
        }

        val notebooks = try {
            notebookRepository.getAll()
        } catch (e: Exception) {
            emptyList<NotebookEntity>()
        }

        val memos = try {
            memoStatusRepository.getAll().filter { it.notebookId == noteBookId }
        } catch (e: Exception) {
            emptyList<MemoStatusView>()
        }

        val messages = try {
            messageRepository.getAll().filter { it.memoId == memoId }
        } catch (e: Exception) {
            emptyList<MessageEntity>()
        }

        withContext(Dispatchers.Main) {
            _selectedNotebook.value = selectedNotebook
            _selectedMemo.value = selectedMemo
            _notebooks.value = notebooks
            _memos.value = memos
            _messages.value = messages
        }
    }

    companion object {
        private const val UNKNOWN_MEMO_ID = -1
        private const val UNKNOWN_NOTEBOOK_ID = -1
        private val ERROR_NOTEBOOK = NotebookEntity.create("")
        private val ERROR_MEMO = MemoStatusView(0, UNKNOWN_NOTEBOOK_ID, "", 0, 0, "")
    }
}
