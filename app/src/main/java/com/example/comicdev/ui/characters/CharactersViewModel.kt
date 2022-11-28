package com.example.comicdev.ui.characters

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.comicdev.api.repository.MarvelRepository
import com.example.comicdev.data.characters.CharacterResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class CharactersViewModel :
    ViewModel() {

    val searchQuery = MutableStateFlow("")

    /*
    @ExperimentalCoroutinesApi
    val searchResult = searchQuery.flatMapLatest { query ->
        marvelRepository.searchCharacter(query).cachedIn(viewModelScope)
    }.asLiveData()

    fun addToFavourite(characterResult: CharacterResult) =
        viewModelScope.launch {
            marvelRepository.addCharacterToFavourite(characterResult)
        }

    fun removeFromFavourite(characterResult: CharacterResult) =
        viewModelScope.launch {
            marvelRepository.removeCharacterFromFavourite(characterResult)
        }

    fun getFavouriteCharacters() =
        marvelRepository.getFavouriteCharacters().asLiveData()*/

}