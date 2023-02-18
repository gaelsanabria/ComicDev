package com.example.comicdev.data.data_source.dto.charactersdto

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)