package jp.kaleidot725.emomemo.data.datasource

import androidx.paging.DataSource
import jp.kaleidot725.emomemo.data.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.data.view.MemoStatusView

class MemoStatusDataSourceFactory(notebookId: Int, repository: MemoStatusRepository) : DataSource.Factory<Int, MemoStatusView>() {
    private val dataSource: MemoStatusDataSource = MemoStatusDataSource(notebookId, repository)
    override fun create(): DataSource<Int, MemoStatusView> {
        return dataSource
    }
}
