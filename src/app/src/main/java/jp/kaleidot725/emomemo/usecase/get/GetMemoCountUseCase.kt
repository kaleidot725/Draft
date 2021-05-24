package jp.kaleidot725.emomemo.usecase.get

import androidx.lifecycle.LiveData

class GetMemoCountUseCase(private val memoRepository: jp.kaleidot725.emomemo.data.repository.MemoRepository) {
    fun execute(notebookId: Int): LiveData<Int> {
        return memoRepository.getMemoCountLiveData(notebookId)
    }
}
