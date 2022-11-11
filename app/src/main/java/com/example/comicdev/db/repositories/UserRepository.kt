package com.example.comicdev.db.repositories

import com.example.comicdev.db.UserDao
import com.example.comicdev.entities.User

class UserRepository(private val userDao : UserDao) {
    suspend fun addUser(user : User){
        userDao.addUser(user)
    }

    fun loadUserData(): List<User> {
        return userDao.loadUser()
    }
}