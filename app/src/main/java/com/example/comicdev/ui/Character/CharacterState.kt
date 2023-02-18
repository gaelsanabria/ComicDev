package com.example.comicdev.ui.Character

import com.example.comicdev.domain.model.CharacterModel

data class CharacterState(
    val isLoading : Boolean = false,
    val characterDetail : List<CharacterModel> = emptyList(),
    val error : String = ""
)