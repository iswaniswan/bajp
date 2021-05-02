package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailEntity(
    var adult: Boolean = false,
    var backdropPath: String? = null,
    var budget: Int = 0,
    var genres: ArrayList<Genre>? = null,
    var homepage: String? = null,
    var id: Int = 0,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double = 0.0,
    var posterPath: String? = null,
    var productionCompanies: ArrayList<Company>? = null,
    var releaseDate: String? = null,
    var revenue: Int = 0,
    var runtime: Int = 0,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var voteAverage: Double = 0.0,
    var voteCount: Double = 0.0
) : Parcelable

