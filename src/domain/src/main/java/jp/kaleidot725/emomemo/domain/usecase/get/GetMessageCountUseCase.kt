package jp.kaleidot725.emomemo.domain.usecase.get

import androidx.lifecycle.LiveData
import jp.kaleidot725.emomemo.data.repository.MessageRepository

class GetMessageCountUseCase(private val messageRepository: MessageRepository) {
    fun execute(memoId: Int): LiveData<Int> {
        return messageRepository.getMessageCountLiveData(memoId)
    }
}
