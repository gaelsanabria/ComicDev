package com.example.comicdev.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.comicdev.data.characters.CharacterResult
import com.example.comicdev.data.comic.ComicResult
import com.example.comicdev.data.series.SeriesResult

object Mapper {

    val CHARACTER_MAPPER = object : DiffUtil.ItemCallback<CharacterResult>() {
        override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CharacterResult,
            newItem: CharacterResult
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }

    val CHARACTER_COMICS_MAPPER = object : DiffUtil.ItemCallback<ComicResult>() {
        override fun areItemsTheSame(oldItem: ComicResult, newItem: ComicResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ComicResult, newItem: ComicResult): Boolean {
            return oldItem.title == newItem.title
        }
    }

    val CHARACTER_SERIES_MAPPER = object : DiffUtil.ItemCallback<SeriesResult>() {
        override fun areItemsTheSame(oldItem: SeriesResult, newItem: SeriesResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SeriesResult, newItem: SeriesResult): Boolean {
            return oldItem.title == newItem.title
        }
    }

}