package jp.kaleidot725.emomemo.data.datasource

import androidx.paging.DataSource
import jp.kaleidot725.emomemo.data.entity.MessageEntity
import jp.kaleidot725.emomemo.data.repository.MessageRepository

class MessageDataSourceFactory(memoId: Int, repository: MessageRepository) : DataSource.Factory<Int, MessageEntity>() {
    private val dataSource: MessageDataSource = MessageDataSource(memoId, repository)
    override fun create(): DataSource<Int, MessageEntity> {
        return dataSource
    }
}
