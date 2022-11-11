package com.example.comicdev.db

import androidx.room.*
import com.example.comicdev.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun loadUser(): List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    fun updateUsers(vararg users: User)
}