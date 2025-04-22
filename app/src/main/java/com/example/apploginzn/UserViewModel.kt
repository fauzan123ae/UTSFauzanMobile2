package com.example.apploginzn.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apploginzn.data.User
import com.example.apploginzn.data.UserDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = UserDatabase.getDatabase(application).userDao()

    fun register(user: User, onSuccess: () -> Unit) {
        viewModelScope.launch {
            userDao.insertUser(user)
            onSuccess()
        }
    }

    fun login(email: String, password: String, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userDao.login(email, password)
            onResult(user)
        }
    }

    fun getUserById(id: Int, onResult: (User?) -> Unit) {
        viewModelScope.launch {
            val user = userDao.getUserById(id)
            onResult(user)
        }
    }

    fun updateUser(user: User, onSuccess: () -> Unit) {
        viewModelScope.launch {
            userDao.updateUser(user)
            onSuccess()
        }
    }

    fun isEmailRegistered(email: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userDao.getUserByEmail(email)
            callback(user != null)
        }
    }
}
