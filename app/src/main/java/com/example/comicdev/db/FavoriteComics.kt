package com.example.comicdev.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteComics(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "url") val Name: String,
)
