package com.example.comicdev.data.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(val data: CharacterData) : Parcelable
