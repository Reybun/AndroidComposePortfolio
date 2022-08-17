package com.yannsimajchel.portfolio.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class PortfolioDataStore(val context: Context) {

    val Context.dataStore by preferencesDataStore(
        name = CV_PLAYER_ONE_DATASTORE
    )

    suspend inline fun <reified T> storeValue(key: Preferences.Key<T>, value: Any) {
        context.dataStore.edit {
            it[key] = value as T
        }
    }

    inline fun <reified T> getValueObs(
        PreferencesKey: Preferences.Key<T>,
    ): Flow<T?> {
        return context.dataStore.data.map {
            it[PreferencesKey]
        }
    }

    suspend inline fun <reified T> getValue(
        PreferencesKey: Preferences.Key<T>,
    ): T? {
        return context.dataStore.data.map {
            it[PreferencesKey]
        }.first()
    }

    suspend fun clearAll() {
        context.dataStore.edit {
            it.clear()
        }
    }

    suspend inline fun <reified T> remove(key: Preferences.Key<T>) {
        context.dataStore.edit { pref ->
            pref.remove(key)
        }
    }

    companion object {
        private const val CV_PLAYER_ONE_DATASTORE = "cv_player_one_datastore"
    }
}