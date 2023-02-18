package com.example.comicdev.data.repository

import androidx.lifecycle.LiveData
import com.example.comicdev.db.UserDao
import com.example.comicdev.entities.User

class UserRepository(private val userDao : UserDao) {
    suspend fun addUser(user : User){
        userDao.addUser(user)
    }

    fun getUser(): LiveData<User> {
        return userDao.getUser()
    }

    fun updateUserData(user: User) {
        return userDao.updateUsers()
    }
}