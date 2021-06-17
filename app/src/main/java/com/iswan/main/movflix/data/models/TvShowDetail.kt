package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowDetail(
    val backdropPath: String,
    val episodeRunTime: Int,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: String,
    val lastAirDate: String,
    val name: String,
    val numberOfSeasons: Int,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<Company>,
    val seasons: List<Season>,
    val status: String,
    val tagline: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavourite: Boolean
):Parcelable
