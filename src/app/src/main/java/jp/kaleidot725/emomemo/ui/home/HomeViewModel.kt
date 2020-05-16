package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import jp.kaleidot725.emomemo.model.entity.Memo
import jp.kaleidot725.emomemo.model.repository.DUMMY_MEMO_LIST
import jp.kaleidot725.emomemo.model.repository.MemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(memoRepository: MemoRepository) : ViewModel() {
    val memoList: LiveData<List<Memo>> = liveData {
        withContext(Dispatchers.IO) {
            emit(memoRepository.getAll() + DUMMY_MEMO_LIST)
        }
    }
}
