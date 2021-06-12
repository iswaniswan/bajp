package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
) : Parcelable
