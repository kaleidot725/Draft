package jp.kaleidot725.emomemo.ui

import androidx.lifecycle.LiveData
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

    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    val selectedNotebook: LiveData<NotebookEntity> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(notebookRepository.getNoteBook(noteBookId))
        }
    }

    val notebooks: LiveData<List<NotebookEntity>> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(notebookRepository.getAll())
        }
    }

    val selectedMemo: LiveData<MemoStatusView> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(memoStatusRepository.getMemo(memoId))
        }
    }

    val memoStatusList: LiveData<List<MemoStatusView>> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(memoStatusRepository.getAll().filter { it.notebookId == noteBookId })
        }
    }

    val messages: LiveData<List<MessageEntity>> = refresh.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(messageRepository.getAll().filter { it.memoId == memoId })
        }
    }

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseInitializeUsecase.execute()
            this@MainViewModel.noteBookId = notebookRepository.getAll().firstOrNull()?.id ?: UNKNOWN_NOTEBOOK_ID
            this@MainViewModel.memoId = memoStatusRepository.getAll().firstOrNull { it.notebookId == noteBookId }?.id ?: UNKNOWN_MEMO_ID

            withContext(Dispatchers.Main) {
                _isCompleted.value = true
                refresh.value = Unit
            }
        }
    }

    fun selectNotebook(id: Int) {
        noteBookId = id
        refresh.value = Unit
    }

    fun selectMemo(id: Int) {
        memoId = id
        refresh.value = Unit
    }

    fun createNotebook(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newNotebook = NotebookEntity.create(title)
            notebookRepository.insert(newNotebook)

            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    fun createMemo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newMemo = MemoEntity.create(noteBookId, title)
            memoRepository.insert(newMemo)

            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    fun createMessage(message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newMessage = MessageEntity.create(memoId, message)
            messageRepository.insert(newMessage)

            withContext(Dispatchers.Main) {
                refresh.value = Unit
            }
        }
    }

    companion object {
        private val UNKNOWN_MEMO_ID = -1
        private val UNKNOWN_NOTEBOOK_ID = -1
    }
}
