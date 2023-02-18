package com.example.comicdev.data.data_source.dto.characterdto

data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)