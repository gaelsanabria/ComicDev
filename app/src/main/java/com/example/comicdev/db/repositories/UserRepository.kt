package com.example.comicdev.db.repositories

import com.example.comicdev.db.UserDao
import com.example.comicdev.entities.User

class UserRepository(private val userDao : UserDao) {
    suspend fun addUser(user : User){
        userDao.addUser(user)
    }

    fun loadUserData(): User {
        return userDao.loadUser()
    }

    fun loadUserName(): List<String> {
        return userDao.loadUserName()
    }

    fun loadUserAge(): List<Int> {
        return userDao.loadUserAge()
    }

    fun loadUserGender(): List<String> {
        return userDao.loadUserGender()
    }

    fun loadUserPic(): List<String> {
        return userDao.loadUserPic()
    }
}