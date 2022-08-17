package com.yannsimajchel.portfolio.application

import android.app.Application
import com.yannsimajchel.portfolio.application.module.domainModule
import com.yannsimajchel.portfolio.application.module.localDataSourceModule
import com.yannsimajchel.portfolio.application.module.repositoryModule
import com.yannsimajchel.portfolio.application.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PortfolioApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@PortfolioApplication)
            // use properties from assets/koin.properties
            androidFileProperties()
            // use modules
            modules(
                viewModelModule(),
                localDataSourceModule(),
                repositoryModule(),
                domainModule(),
            )
        }

    }
}