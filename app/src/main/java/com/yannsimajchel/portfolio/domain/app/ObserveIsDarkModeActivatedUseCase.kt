package com.yannsimajchel.portfolio.domain.app

import com.yannsimajchel.portfolio.data.repository.AppRepo
import kotlinx.coroutines.flow.Flow

class ObserveIsDarkModeActivatedUseCase(
    private val appRepo: AppRepo,
) {
    operator fun invoke(): Flow<Boolean> {
        return appRepo.observeIsDarkModeActivated()
    }
}