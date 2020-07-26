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
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import jp.kaleidot725.emomemo.usecase.DatabaseInitializeUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val memoRepository: MemoRepository,
    private val messageRepository: MessageRepository,
    private val notebookRepository: NotebookRepository,
    private val databaseInitializeUsecase: DatabaseInitializeUsecase
) : ViewModel() {
    private val _isCompleted: LiveEvent<Boolean> = LiveEvent()
    val isCompleted: LiveData<Boolean> = _isCompleted

    private val _memoId: MutableLiveData<Int> = MutableLiveData(UNKNOWN_MEMO_ID)
    val memoId: LiveData<Int> = _memoId

    private val _notebookId: MutableLiveData<Int> = MutableLiveData(UNKNOWN_NOTEBOOK_ID)
    val notebookId: LiveData<Int> = _notebookId

    val notebooks: LiveData<List<NotebookEntity>> = isCompleted.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(notebookRepository.getAll())
        }
    }

    val memos: LiveData<List<MemoEntity>> = notebookId.switchMap {
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(memoRepository.getAll())
        }
    }

    val messages: LiveData<List<MessageEntity>> = memoId.switchMap { memoId ->
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emit(messageRepository.getMessage(memoId))
        }
    }

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseInitializeUsecase.execute()

            val notebookId = notebookRepository.getAll().firstOrNull()?.id ?: UNKNOWN_NOTEBOOK_ID
            val memoId = memoRepository.getAll().filter { it.notebookId == notebookId }.firstOrNull()?.id ?: UNKNOWN_MEMO_ID

            withContext(Dispatchers.Main) {
                _notebookId.value = notebookId
                _memoId.value = memoId
                _isCompleted.value = true
            }
        }
    }

    fun selectNotebook(notebookId: Int) {
        _notebookId.value = notebookId
    }

    fun selectMemo(memoId: Int) {
        _memoId.value = memoId
    }

    fun createMemo(title: String) {

    }

    companion object {
        val UNKNOWN_MEMO_ID = -1
        val UNKNOWN_NOTEBOOK_ID = -1
    }
}
