package com.example.bottomnavigationapplication.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(context: Context) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    // 读取用户名
    val username: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.USERNAME]
        }

    // 读取登录状态
    val isLoggedIn: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] ?: false
        }

    // 读取登录次数
    val loginCount: Flow<Int> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PreferencesKeys.LOGIN_COUNT] ?: 0
        }

    // 保存用户名
    suspend fun saveUsername(name: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USERNAME] = name
        }
    }

    // 设置登录状态
    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOGGED_IN] = isLoggedIn
        }
    }

    // 增加登录次数
    suspend fun incrementLoginCount() {
        val currentCount = dataStore.data.first()[PreferencesKeys.LOGIN_COUNT] ?: 0
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LOGIN_COUNT] = currentCount + 1
        }
    }
}