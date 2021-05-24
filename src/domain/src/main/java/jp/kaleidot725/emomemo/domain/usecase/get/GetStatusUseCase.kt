package jp.kaleidot725.emomemo.domain.usecase.get

class GetStatusUseCase(private val statusRepository: jp.kaleidot725.emomemo.data.repository.StatusRepository) {
    suspend fun execute(): jp.kaleidot725.emomemo.data.entity.StatusEntity {
        return statusRepository.get() ?: jp.kaleidot725.emomemo.data.entity.StatusEntity()
    }
}
