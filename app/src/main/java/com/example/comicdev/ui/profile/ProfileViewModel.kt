package com.example.comicdev.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.example.comicdev.db.AppDatabase
import com.example.comicdev.data.repository.UserRepository
import com.example.comicdev.entities.User
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    private val repository : UserRepository
    val getUser : LiveData<User>
    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getUser = repository.getUser()
    }

    fun addUser(user : User){
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    fun updateUser(user : User) {
        viewModelScope.launch {
            repository.updateUserData(user)
        }
    }
}