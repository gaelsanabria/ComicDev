package com.example.comicdev.data.characters

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterData(val results: List<CharacterResult>) : Parcelable
