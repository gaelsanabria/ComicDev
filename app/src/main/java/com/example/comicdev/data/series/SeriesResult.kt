package com.example.comicdev.data.series

data class SeriesResult(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: SeriesThumbnail
) {
    data class SeriesThumbnail(val path: String, val extension: String)
}