package com.yannsimajchel.portfolio.application.module

import com.yannsimajchel.portfolio.domain.app.ObserveIsDarkModeActivatedUseCase
import com.yannsimajchel.portfolio.domain.app.UpdateDarkModeUseCase
import org.koin.dsl.module

fun domainModule() = module {
    factory { ObserveIsDarkModeActivatedUseCase(get()) }
    factory { UpdateDarkModeUseCase(get()) }
}