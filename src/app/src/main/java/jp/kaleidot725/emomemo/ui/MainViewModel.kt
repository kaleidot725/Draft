package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.DatabaseInitializeUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    val memos: LiveData<PagedList<MemoStatusView>> = _selectedNotebook.switchMap {
        val factory = if (it == ERROR_NOTEBOOK) MemoStatusDataNullSourceFactory() else MemoStatusDataSourceFactory(it.id, memoStatusRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        LivePagedListBuilder(factory, config).build()
    }

    val messages: LiveData<PagedList<MessageEntity>?> = _selectedMemo.switchMap {
        val factory = if (it == ERROR_MEMO) MessageDataNullSourceFactory() else MessageDataSourceFactory(it.id, messageRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        LivePagedListBuilder(factory, config).build()
    }

    val emptyStatus: LiveData<EmptyStatus> = MediatorLiveData<EmptyStatus>().apply {
        fun getEmptyStatus() {
            if (loading.value == true) {
                this.value = EmptyStatus.NO_ERROR
                return
            }

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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            databaseInitializeUsecase.execute()
            fetchData(true)

            withContext(Dispatchers.Main) {
                _initialized.value = true
            }
        }
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
        viewModelScope.launch {
            _loading.value = true
            _notebooks.value = emptyList()
            _selectedNotebook.value = ERROR_NOTEBOOK
            _selectedMemo.value = ERROR_MEMO

            if (reselect) {
                this@MainViewModel.noteBook = try {
                    notebookRepository.first() ?: ERROR_NOTEBOOK
                } catch (e: Exception) {
                    ERROR_NOTEBOOK
                }

                this@MainViewModel.memo = try {
                    memoStatusRepository.firstByNotebookId(this@MainViewModel.noteBook.id) ?: ERROR_MEMO
                } catch (e: Exception) {
                    ERROR_MEMO
                }
            }

            val notebooks = try {
                notebookRepository.getAll()
            } catch (e: Exception) {
                emptyList<NotebookEntity>()
            }

            // FIXME なんとか処理を直列にしてこの delay をなくす
            delay(300)

            _notebooks.value = notebooks
            _selectedNotebook.value = this@MainViewModel.noteBook
            _selectedMemo.value = this@MainViewModel.memo
            _loading.value = false
        }
    }

    companion object {
        private val ERROR_NOTEBOOK = NotebookEntity.create("")
        private val ERROR_MEMO = MemoStatusView(0, 0, "", 0, 0, "")
    }
}
