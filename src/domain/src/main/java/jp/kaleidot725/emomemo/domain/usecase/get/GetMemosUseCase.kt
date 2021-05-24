package jp.kaleidot725.emomemo.domain.usecase.get

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class GetMemosUseCase(private val memoStatusRepository: jp.kaleidot725.emomemo.data.repository.MemoStatusRepository) {
    suspend fun execute(notebookId: Int): List<jp.kaleidot725.emomemo.data.view.MemoStatusView> {
        return memoStatusRepository.getAll()
    }

    fun executeLiveData(notebookId: Int): LiveData<PagedList<jp.kaleidot725.emomemo.data.view.MemoStatusView>> {
        val factory = jp.kaleidot725.emomemo.data.datasource.MemoStatusDataSourceFactory(notebookId, memoStatusRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        return LivePagedListBuilder(factory, config).build()
    }
}
