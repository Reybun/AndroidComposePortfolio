package com.yannsimajchel.portfolio.application.module

import com.yannsimajchel.portfolio.data.datastore.PortfolioDataStore
import com.yannsimajchel.portfolio.data.local_data_source.AppLocalDataSource
import com.yannsimajchel.portfolio.data.local_data_source.AppLocalDataSourceImpl
import org.koin.dsl.module

fun localDataSourceModule() = module {
    single { PortfolioDataStore(get()) }
    factory { AppLocalDataSourceImpl(get()) as AppLocalDataSource }
}