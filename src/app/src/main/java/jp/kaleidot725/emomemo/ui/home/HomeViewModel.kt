package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import jp.kaleidot725.emomemo.model.db.datasource.MemoStatusDataSourceFactory
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.MemoStatusRepository
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.ObserveMemoCountUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val statusRepository: StatusRepository,
    private val observeMemoCountUseCase: ObserveMemoCountUseCase,
    private val memoStatusRepository: MemoStatusRepository
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // TODO 未実装
    private val _canAddNotebook: MutableLiveData<Boolean> = MutableLiveData(true)
    val canAddNotebook: LiveData<Boolean> = _canAddNotebook

    private val status: LiveData<StatusEntity> = statusRepository.get()
    private val statusObserver: Observer<StatusEntity> = Observer { status ->
        observeMemoCountUseCase.dispose()
        observeMemoCountUseCase.execute(status.notebookId) { count.value = it }
    }

    private val count: MutableLiveData<Int> = MutableLiveData()
    val memos: LiveData<PagedList<MemoStatusView>> = count.switchMap {
        createMemoPagedListBuilder()
    }.distinctUntilChanged()

    private val selectedNotebookId get() = status.value?.notebookId ?: 0

    init {
        status.observeForever(statusObserver)
    }

    fun select(memo: MemoStatusView) {
        viewModelScope.launch {
            statusRepository.update(selectedNotebookId, memo.id)
        }
    }

    private fun createMemoPagedListBuilder(): LiveData<PagedList<MemoStatusView>> {
        val factory = MemoStatusDataSourceFactory(selectedNotebookId, memoStatusRepository)
        val config = PagedList.Config.Builder().setInitialLoadSizeHint(10).setPageSize(10).build()
        return LivePagedListBuilder(factory, config).build()
    }

    override fun onCleared() {
        status.removeObserver(statusObserver)
    }

}
