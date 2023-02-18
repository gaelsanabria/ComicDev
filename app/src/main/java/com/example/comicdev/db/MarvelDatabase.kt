package com.example.comicdev.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.comicdev.data.characters.CharacterResult
import com.example.comicdev.utils.Constants

@Database(entities = [CharacterResult::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

}