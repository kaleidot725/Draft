package jp.kaleidot725.emomemo.usecase

import jp.kaleidot725.emomemo.model.db.entity.StatusEntity
import jp.kaleidot725.emomemo.model.db.repository.StatusRepository

class GetStatusUseCase(private val statusRepository: StatusRepository) {
    suspend fun execute(): StatusEntity? {
        return statusRepository.get()
    }
}
