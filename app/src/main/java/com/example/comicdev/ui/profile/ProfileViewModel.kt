package com.example.comicdev.ui.profile

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.comicdev.db.AppDatabase
import com.example.comicdev.db.repositories.UserRepository
import com.example.comicdev.entities.User
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    private val repository : UserRepository
    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun addUser(user : User){
        viewModelScope.launch {
            repository.addUser(user)
        }
    }
}