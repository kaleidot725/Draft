package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.entity.NotebookEntity
import jp.kaleidot725.emomemo.data.repository.NotebookRepository
import kotlinx.coroutines.flow.Flow

class GetNotebooksUseCase(private val notebookRepository: NotebookRepository) {
    fun execute(): Flow<List<NotebookEntity>> {
        return notebookRepository.getAllFlow()
    }
}