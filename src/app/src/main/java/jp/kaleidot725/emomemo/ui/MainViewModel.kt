package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
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
    private var memo: MemoStatusView = ERROR_MEMO
    private var noteBook: NotebookEntity = ERROR_NOTEBOOK

    private val _initialized: LiveEvent<Boolean> = LiveEvent()
    val initialized: LiveData<Boolean> = _initialized

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

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

    val notFoundNotebook: LiveData<Boolean> = selectedNotebook.map { it == ERROR_NOTEBOOK }

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
            fetchData(noteBook.id == ERROR_NOTEBOOK.id)
        }
    }

    fun selectNotebook(notebook: NotebookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            this@MainViewModel.noteBook = notebook
            fetchData(false)
        }
    }

    fun deleteNotebook(notebook: NotebookEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            notebookRepository.delete(notebook)
            fetchData(this@MainViewModel.noteBook.id == notebook.id)
        }
    }

    fun createMemo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.insert(MemoEntity.create(noteBook.id, title))
            fetchData(false)
        }
    }

    fun selectMemo(memo: MemoStatusView) {
        viewModelScope.launch(Dispatchers.IO) {
            this@MainViewModel.memo = memo
            fetchData(false)
        }
    }

    fun deleteMemo(memo: MemoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            memoRepository.delete(memo)
            fetchData(this@MainViewModel.memo.id == memo.id)
        }
    }

    fun createMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.insert(MessageEntity.create(memo.id, message))
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
        withContext(Dispatchers.Main) {
            _loading.value = true
        }

        if (reselect) {
            this.noteBook = try {
                notebookRepository.first() ?: ERROR_NOTEBOOK
            } catch (e: Exception) {
                ERROR_NOTEBOOK
            }

            this.memo = try {
                memoStatusRepository.firstByNotebookId(this.noteBook.id) ?: ERROR_MEMO
            } catch (e: Exception) {
                ERROR_MEMO
            }
        }

        val notebooks = try {
            notebookRepository.getAll()
        } catch (e: Exception) {
            emptyList<NotebookEntity>()
        }

        val memos = try {
            memoStatusRepository.getMemoListByNotebookId(this.noteBook.id)
        } catch (e: Exception) {
            emptyList<MemoStatusView>()
        }

        val messages = try {
            messageRepository.getMessagesByMemoId(this.memo.id)
        } catch (e: Exception) {
            emptyList<MessageEntity>()
        }

        withContext(Dispatchers.Main) {
            _selectedNotebook.value = this@MainViewModel.noteBook
            _selectedMemo.value = this@MainViewModel.memo
            _notebooks.value = notebooks
            _memos.value = memos
            _messages.value = messages
            _loading.value = false
        }
    }

    companion object {
        private val ERROR_NOTEBOOK = NotebookEntity.create("")
        private val ERROR_MEMO = MemoStatusView(0, 0, "", 0, 0, "")
    }
}
