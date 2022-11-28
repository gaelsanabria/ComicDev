package com.example.comicdev.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.comicdev.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Query("SELECT name FROM user")
    fun loadUserName(): List<String>

    @Query("SELECT gender FROM user")
    fun loadUserGender(): List<String>

    @Query("SELECT age FROM user")
    fun loadUserAge(): List<Int>

    @Query("SELECT picture FROM user")
    fun loadUserPic(): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(vararg users: User)
}