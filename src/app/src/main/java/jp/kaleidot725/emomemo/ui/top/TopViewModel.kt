package jp.kaleidot725.emomemo.ui.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.emomemo.model.AppStatus
import jp.kaleidot725.emomemo.model.db.entity.NotebookEntity
import jp.kaleidot725.emomemo.model.db.repository.NotebookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TopViewModel(
    private val appStatus: AppStatus,
    private val notebookRepository: NotebookRepository
) : ViewModel() {
    fun initialize() {
        // FIXME なにもなければ作成するようにしている
        runBlocking {
            viewModelScope.launch(Dispatchers.IO) {
                var notebooks = notebookRepository.getAll()
                if (notebooks.count() <= 0) {
                    notebookRepository.insert(NotebookEntity(0, "Default"))
                }

                notebooks = notebookRepository.getAll()
                appStatus.notebookId = notebooks.first().id
            }.join()
        }
    }
}
