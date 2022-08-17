package com.yannsimajchel.portfolio.application.module

import com.yannsimajchel.portfolio.MainViewModel
import com.yannsimajchel.portfolio.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
}