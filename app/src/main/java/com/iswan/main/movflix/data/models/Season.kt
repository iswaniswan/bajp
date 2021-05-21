package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    var airDate: String,
    var episodeCount: Int,
    var id: Int,
    var name: String,
    var overview: String,
    var posterPath: String,
    var seasonNumber: Int
): Parcelable