package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.repository.MemoRepository

class GetMemoCountUseCase(private val memoRepository: MemoRepository) {
    fun execute(notebookId: Int): LiveData<Int> {
        return memoRepository.getMemoCountLiveData(notebookId)
    }
}
