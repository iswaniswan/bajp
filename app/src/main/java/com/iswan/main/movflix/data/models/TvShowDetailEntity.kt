package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowDetailEntity(
    var backdropPath: String? = null,
    var episodeRunTime: Int = 0,
    var firstAirDate: String? = null,
    var genres: ArrayList<Genre>? = null,
    var homepage: String? = null,
    var id: Int = 0,
    var lastAirDate: String? = null,
    var name: String? = null,
    var numberOfSeasons: Int = 0,
    var originalLanguage: String? = null,
    var overview: String? = null,
    var popularity: Double = 0.0,
    var posterPath: String? = null,
    var productionCompanies: ArrayList<Company> = arrayListOf(),
    var seasons: ArrayList<Season> = arrayListOf(),
    var status: String? = null,
    var tagline: String? = null,
    var voteAverage: Double = 0.0,
    var voteCount: Double = 0.0
):Parcelable
