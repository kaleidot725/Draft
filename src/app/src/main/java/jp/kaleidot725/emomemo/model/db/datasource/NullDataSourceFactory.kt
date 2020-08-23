package jp.kaleidot725.emomemo.model.db.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import jp.kaleidot725.emomemo.model.db.entity.MessageEntity
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusDataNullSourceFactory : DataSource.Factory<Int, MemoStatusView>() {
    override fun create(): DataSource<Int, MemoStatusView> {
        return object : PageKeyedDataSource<Int, MemoStatusView>() {
            override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MemoStatusView>) {
                callback.onResult(emptyList(), null, 0)
            }

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MemoStatusView>) {
                callback.onResult(emptyList(), 0)
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MemoStatusView>) {
                callback.onResult(emptyList(), 0)
            }
        }
    }
}

class MessageDataNullSourceFactory : DataSource.Factory<Int, MessageEntity>() {
    override fun create(): DataSource<Int, MessageEntity> {
        return object : PageKeyedDataSource<Int, MessageEntity>() {
            override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MessageEntity>) {
                callback.onResult(emptyList(), null, 0)
            }

            override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MessageEntity>) {
                callback.onResult(emptyList(), 0)
            }

            override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MessageEntity>) {
                callback.onResult(emptyList(), 0)
            }
        }
    }
}
