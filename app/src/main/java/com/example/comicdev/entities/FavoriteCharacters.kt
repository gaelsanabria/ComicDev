package com.example.comicdev.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCharacters(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "url") val Name: String,
)
