package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShow(
    val id: String,
    val posterPath: String,
    val overview: String,
    val firstAirDate: String,
    val name: String,
    val voteAverage: Double,
) : Parcelable
