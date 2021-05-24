package jp.kaleidot725.emomemo.usecase.get

import androidx.lifecycle.LiveData

class GetMessageCountUseCase(private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository) {
    fun execute(memoId: Int): LiveData<Int> {
        return messageRepository.getMessageCountLiveData(memoId)
    }
}
