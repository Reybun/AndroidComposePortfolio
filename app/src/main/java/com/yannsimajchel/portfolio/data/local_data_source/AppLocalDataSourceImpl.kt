package com.yannsimajchel.portfolio.data.local_data_source

import com.yannsimajchel.portfolio.data.datastore.PortfolioDataStore
import com.yannsimajchel.portfolio.data.datastore.PreferenceKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppLocalDataSourceImpl(
    private val datastore: PortfolioDataStore,
) : AppLocalDataSource {
    override fun observeIsDarkModeActivated(): Flow<Boolean> {
        return datastore.getValueObs(PreferenceKeys.IS_DARK_MODE).map {
            it ?: false
        }
    }

    override suspend fun updateDarkMode(isActivated: Boolean) {
        datastore.storeValue(PreferenceKeys.IS_DARK_MODE, isActivated)
    }

}