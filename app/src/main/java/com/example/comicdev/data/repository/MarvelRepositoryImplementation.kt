package com.example.comicdev.data.repository

import com.example.comicdev.data.data_source.dto.characterdto.CharacterDTO
import com.example.comicdev.data.data_source.dto.charactersdto.CharactersDTO
import com.example.comicdev.domain.repository.MarvelRepository
import com.oyelabs.marvel.universe.data.data_source.dto.MarvelApi
import javax.inject.Inject

class MarvelRepositoryImplementation @Inject constructor(
    private val api : MarvelApi
) : MarvelRepository {
    override suspend fun getAllCharacters(offset:Int): CharactersDTO {
        return api.getAllCharacters(offset=offset.toString())
    }

    override suspend fun getAllSearchedCharacters(search: String): CharactersDTO {
        return api.getAllSearchedCharacters(search=search)
    }

    override suspend fun getCharacterById(id: String): CharacterDTO {
        return api.getCharacterById(id)
    }
}