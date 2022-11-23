package com.example.comicdev.db

import androidx.room.*
import com.example.comicdev.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun loadUser(): User

    @Query("SELECT name FROM user")
    fun loadUserName(): List<String>

    @Query("SELECT gender FROM user")
    fun loadUserGender(): List<String>

    @Query("SELECT age FROM user")
    fun loadUserAge(): List<Int>

    @Query("SELECT picture FROM user")
    fun loadUserPic(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    fun updateUsers(vararg users: User)
}