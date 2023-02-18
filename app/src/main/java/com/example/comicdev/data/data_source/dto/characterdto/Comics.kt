package com.example.comicdev.data.data_source.dto.characterdto

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)