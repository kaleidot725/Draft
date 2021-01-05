package jp.kaleidot725.emomemo.usecase

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jp.kaleidot725.emomemo.model.db.datasource.MessageDataSourceFactory
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class GetMessageUseCase(private val messageRepository: MessageRepository) {
    fun execute(memoId: Int): LiveData<PagedList<MessageEntity>> {
        val factory = MessageDataSourceFactory(memoId, messageRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(100).setPageSize(100).build()
        return LivePagedListBuilder(factory, config).build()
    }
}
