package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity(
    var id: Int? = 0,
    var posterPath: String? = null,
    var overview: String? = null,
    var firstAirDate: String? = null,
    var name: String? = null,
    var voteAverage: Double? = 0.0,
) : Parcelable
