package jp.kaleidot725.emomemo.domain.usecase.get

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class GetMessageUseCase(private val messageRepository: jp.kaleidot725.emomemo.data.repository.MessageRepository) {
    fun execute(memoId: Int): LiveData<PagedList<jp.kaleidot725.emomemo.data.entity.MessageEntity>> {
        val factory = jp.kaleidot725.emomemo.data.datasource.MessageDataSourceFactory(memoId, messageRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(100).setPageSize(100).build()
        return LivePagedListBuilder(factory, config).build()
    }
}
