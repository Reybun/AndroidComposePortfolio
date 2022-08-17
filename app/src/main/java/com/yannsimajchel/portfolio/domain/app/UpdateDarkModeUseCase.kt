package com.yannsimajchel.portfolio.domain.app

import com.yannsimajchel.portfolio.data.repository.AppRepo

class UpdateDarkModeUseCase(
    private val appRepo: AppRepo,
) {
    suspend operator fun invoke(isActivated: Boolean) {
        appRepo.updateDarkMode(isActivated)
    }
}