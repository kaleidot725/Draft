package jp.kaleidot725.emomemo.usecase.get

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class GetMessageCountUseCase(private val messageRepository: MessageRepository) {
    fun execute(memoId: Int): LiveData<Int> {
        return messageRepository.getMessageCountLiveData(memoId)
    }
}
