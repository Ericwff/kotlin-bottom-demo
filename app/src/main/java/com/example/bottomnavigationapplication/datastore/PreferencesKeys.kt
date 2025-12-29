package com.example.bottomnavigationapplication.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val USERNAME = stringPreferencesKey("username")
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    val LOGIN_COUNT = intPreferencesKey("login_count")
}