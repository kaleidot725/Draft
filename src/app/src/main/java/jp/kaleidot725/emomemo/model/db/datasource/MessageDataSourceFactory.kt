package jp.kaleidot725.emomemo.model.db.datasource

import androidx.paging.DataSource
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.repository.MessageRepository

class MessageDataSourceFactory(id: Int, repository: MessageRepository) : DataSource.Factory<Int, MessageEntity>() {
    private val source = MessageDataSource(id, repository)

    override fun create(): DataSource<Int, MessageEntity> {
        return source
    }
}
