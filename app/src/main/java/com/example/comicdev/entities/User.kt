package com.example.comicdev.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val Name: String,
    @ColumnInfo(name = "gender") val Gender: String,
    @ColumnInfo(name = "age") val Age: Int,
    @ColumnInfo(name = "picture") val Picture: String?
)
