package jp.kaleidot725.emomemo.domain.usecase.update

class UpdateNotebookUseCase(private val notebookRepository: jp.kaleidot725.emomemo.data.repository.NotebookRepository) {
    suspend fun execute(notebookEntity: jp.kaleidot725.emomemo.data.entity.NotebookEntity, value: String) {
        val new = jp.kaleidot725.emomemo.data.entity.NotebookEntity(notebookEntity.id, value)
        notebookRepository.update(new)
    }
}
