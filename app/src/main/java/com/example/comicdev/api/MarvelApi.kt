package com.example.comicdev.api

import com.example.comicdev.data.characters.CharacterResponse
import com.example.comicdev.data.comic.ComicResponse
import com.example.comicdev.data.series.SeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("comics")
    suspend fun getAllComics(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): ComicResponse

    @GET("characters")
    suspend fun getAllCharacters(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): CharacterResponse

    @GET("characters")
    suspend fun searchCharacter(
        @Query("nameStartsWith") query: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): CharacterResponse

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(
        @Path("characterId") characterId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): ComicResponse

    @GET("characters/{characterId}/series")
    suspend fun getCharacterSeries(
        @Path("characterId") characterId: String,
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): SeriesResponse
}