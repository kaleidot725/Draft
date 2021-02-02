package jp.kaleidot725.emomemo.usecase.get

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jp.kaleidot725.emomemo.model.db.datasource.MemoStatusDataSourceFactory
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView

class GetMemosUseCase(private val memoStatusRepository: MemoStatusRepository) {
    suspend fun execute(notebookId: Int): List<MemoStatusView> {
        return memoStatusRepository.getAll()
    }

    fun executeLiveData(notebookId: Int): LiveData<PagedList<MemoStatusView>> {
        val factory = MemoStatusDataSourceFactory(notebookId, memoStatusRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        return LivePagedListBuilder(factory, config).build()
    }
}
