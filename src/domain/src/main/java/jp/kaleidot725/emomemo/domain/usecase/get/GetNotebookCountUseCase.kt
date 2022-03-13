package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.repository.NotebookRepository
import kotlinx.coroutines.flow.Flow

class GetNotebookCountUseCase(private val notebookRepository: NotebookRepository) {
    fun execute(): Flow<Int> {
        return notebookRepository.getNotebookCountFlow()
    }
}
