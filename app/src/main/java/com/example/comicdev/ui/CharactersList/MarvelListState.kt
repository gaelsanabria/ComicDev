package com.example.comicdev.ui.CharactersList

import com.example.comicdev.domain.model.CharacterModel

data class MarvelListState(
    val isLoading : Boolean = false,
    val charactersList : List<CharacterModel> = emptyList(),
    val error : String = ""
)