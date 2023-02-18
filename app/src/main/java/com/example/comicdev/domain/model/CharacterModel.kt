package com.example.comicdev.domain.model

data class CharacterModel(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail : String,
    val thumbnailExt: String,
    val comics : List<String>,
)