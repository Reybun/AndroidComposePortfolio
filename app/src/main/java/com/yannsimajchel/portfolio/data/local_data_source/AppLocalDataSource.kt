package com.yannsimajchel.portfolio.data.local_data_source

import kotlinx.coroutines.flow.Flow

interface AppLocalDataSource {

    fun observeIsDarkModeActivated(): Flow<Boolean>

    suspend fun updateDarkMode(isActivated: Boolean)
}