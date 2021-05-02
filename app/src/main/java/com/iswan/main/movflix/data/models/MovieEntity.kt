package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var id: Int? = 0,
    var posterPath: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var voteAverage: Double? = 0.0,
) : Parcelable
