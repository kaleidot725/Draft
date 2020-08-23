package jp.kaleidot725.emomemo.model.db.datasource

import androidx.paging.DataSource
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class MemoStatusDataSourceFactory(repository: MemoStatusRepository) : DataSource.Factory<Int, MemoStatusView>() {
    private val source = MemoStatusDataSource(repository)

    override fun create(): DataSource<Int, MemoStatusView> {
        return source
    }
}
