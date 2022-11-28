package com.example.comicdev.db

import androidx.room.*
import com.example.comicdev.data.characters.CharacterResult
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterResult)

    @Delete
    suspend fun delete(character: CharacterResult)

    @Query("SELECT * FROM character_table")
    fun getFavouriteCharacters(): Flow<List<CharacterResult>>

}