package com.example.comicdev.domain.repository

import com.example.comicdev.data.data_source.dto.characterdto.CharacterDTO
import com.example.comicdev.data.data_source.dto.charactersdto.CharactersDTO

interface MarvelRepository {

    suspend fun getAllCharacters(offset:Int):CharactersDTO
    suspend fun getAllSearchedCharacters(search:String): CharactersDTO
    suspend fun getCharacterById(id:String): CharacterDTO
}