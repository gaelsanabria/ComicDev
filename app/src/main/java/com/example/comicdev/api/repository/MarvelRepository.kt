package com.example.comicdev.api.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.comicdev.api.MarvelApi
import com.example.comicdev.data.characters.CharacterResult
import com.example.comicdev.db.CharacterDao
import com.example.comicdev.ui.characters.CharacterPagingSource
//import com.example.comicdev.ui.characters.SeriesPagingSource
import com.example.comicdev.ui.comics.ComicPagingSource

class MarvelRepository constructor(
    private val marvelApi: MarvelApi,
    private val characterDao: CharacterDao
) {

    fun searchCharacter(query: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { CharacterPagingSource(marvelApi, query) }
    ).flow

    fun getCharacterComics(characterId: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
        pagingSourceFactory = { ComicPagingSource(marvelApi, characterId) }
    ).flow

    /*
    fun getCharacterSeries(characterId: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
        pagingSourceFactory = { SeriesPagingSource(marvelApi, characterId) }
    ).flow*/

    suspend fun addCharacterToFavourite(characterResult: CharacterResult) =
        characterDao.insert(characterResult)

    suspend fun removeCharacterFromFavourite(characterResult: CharacterResult) =
        characterDao.delete(characterResult)

    fun getFavouriteCharacters() =
        characterDao.getFavouriteCharacters()

}