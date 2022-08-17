package com.yannsimajchel.portfolio.application.module

import com.yannsimajchel.portfolio.data.repository.AppRepo
import com.yannsimajchel.portfolio.data.repository.AppRepoImpl
import org.koin.dsl.module

fun repositoryModule() = module {
    single { AppRepoImpl(get()) as AppRepo }
}