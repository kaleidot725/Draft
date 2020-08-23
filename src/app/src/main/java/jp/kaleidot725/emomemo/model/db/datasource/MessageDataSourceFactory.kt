package jp.kaleidot725.emomemo.model.db.datasource

import androidx.paging.DataSource
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class MessageDataSourceFactory(memoId: Int, repository: MessageRepository) : DataSource.Factory<Int, MessageEntity>() {
    private val dataSource: MessageDataSource = MessageDataSource(memoId, repository)
    override fun create(): DataSource<Int, MessageEntity> {
        return dataSource
    }
}
