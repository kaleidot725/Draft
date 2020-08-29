package jp.kaleidot725.emomemo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.view.MemoStatusView
import jp.kaleidot725.emomemo.usecase.GetMemoUseCase
import jp.kaleidot725.emomemo.usecase.ObserveMemoCountUseCase
import jp.kaleidot725.emomemo.usecase.ObserveStatusUseCase
import jp.kaleidot725.emomemo.usecase.SelectMemoUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeStatusUseCase: ObserveStatusUseCase,
    private val observeMemoCountUseCase: ObserveMemoCountUseCase,
    private val getMemoUseCase: GetMemoUseCase,
    private val selectMemoUseCase: SelectMemoUseCase
) : ViewModel() {
    // TODO 未実装
    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    // TODO 未実装
    private val _canAddNotebook: MutableLiveData<Boolean> = MutableLiveData(true)
    val canAddNotebook: LiveData<Boolean> = _canAddNotebook

    private val status: MutableLiveData<Pair<StatusEntity, Int>> = MutableLiveData()
    val memos: LiveData<PagedList<MemoStatusView>> = status.switchMap { getMemoUseCase.execute(it.first.notebookId) }.distinctUntilChanged()

    init {
        observeStatusUseCase.execute { newStatus ->
            newStatus ?: return@execute
            observeMemoCountUseCase.dispose()
            observeMemoCountUseCase.execute(newStatus.notebookId) { count ->
                status.value = Pair(newStatus, count)
            }
        }
    }

    fun select(memo: MemoStatusView) {
        viewModelScope.launch {
            selectMemoUseCase.execute(memo.id)
        }
    }

    override fun onCleared() {
        observeStatusUseCase.dispose()
        observeMemoCountUseCase.dispose()
    }
}
