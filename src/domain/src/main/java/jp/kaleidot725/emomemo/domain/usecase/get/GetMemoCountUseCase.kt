package jp.kaleidot725.emomemo.domain.usecase.get

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.data.repository.MemoRepository

class GetMemoCountUseCase(private val memoRepository: MemoRepository) {
    fun execute(notebookId: Int): LiveData<Int> {
        return memoRepository.getMemoCountLiveData(notebookId)
    }
}
