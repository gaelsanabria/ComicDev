package com.example.comicdev.ui.Character

import androidx.lifecycle.*
import com.example.comicdev.domain.use_cases.CharacterUseCase
import com.example.comicdev.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
) :ViewModel(){

    private val characterValue = MutableStateFlow(CharacterState())
    val _characterValue : StateFlow<CharacterState> = characterValue

    fun getCharacterByIdValue(id:String)=viewModelScope.launch{
        characterUseCase(id).collect {
            when(it){
                is State.Success-> {
                    characterValue.value = CharacterState(
                        characterDetail = it.data?: emptyList()
                    )
                }
                is State.Loading->{
                    characterValue.value = CharacterState(isLoading = true)
                }
                is State.Error->{
                    characterValue.value = CharacterState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
}