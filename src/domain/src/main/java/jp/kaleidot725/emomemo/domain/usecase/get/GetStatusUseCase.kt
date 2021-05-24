package jp.kaleidot725.emomemo.domain.usecase.get

import jp.kaleidot725.emomemo.data.entity.StatusEntity
import jp.kaleidot725.emomemo.data.repository.StatusRepository

class GetStatusUseCase(private val statusRepository: StatusRepository) {
    suspend fun execute(): StatusEntity {
        return statusRepository.get() ?: StatusEntity()
    }
}
