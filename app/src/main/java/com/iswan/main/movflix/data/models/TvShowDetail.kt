package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowDetail(
    val backdropPath: String,
    val episodeRunTime: Int,
    val firstAirDate: String,
    val genres: ArrayList<Genre>,
    val homepage: String,
    val id: Int,
    val lastAirDate: String,
    val name: String,
    val numberOfSeasons: Int,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: ArrayList<Company>,
    val seasons: ArrayList<Season>,
    val status: String,
    val tagline: String,
    val voteAverage: Double,
    val voteCount: Int
):Parcelable
