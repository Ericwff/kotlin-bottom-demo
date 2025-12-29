package com.example.bottomnavigationapplication.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottomnavigationapplication.datastore.UserPreferencesRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserPreferencesRepository) : ViewModel() {

    val username = repository.username
    val isLoggedIn = repository.isLoggedIn
    val loginCount = repository.loginCount

    fun saveUser(name: String) {
        viewModelScope.launch {
            repository.saveUsername(name)
            repository.setLoggedIn(true)
            repository.incrementLoginCount()
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.setLoggedIn(false)
        }
    }
}