package com.azhel.gogonedemo.domain.usecases

import com.azhel.gogonedemo.domain.Repository

class UpdateVerbsToLearnUseCase(private val repository: Repository) {

    suspend fun execute(changes: Map<Int, Boolean>): Result<Unit> {
        return repository.toggleLearning(changes)
    }
}
