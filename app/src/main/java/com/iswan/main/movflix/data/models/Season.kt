package com.iswan.main.movflix.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Season(
    var airDate: String? = null,
    var episodeCount: Int = 0,
    var id: Int = 0,
    var name: String? = null,
    var overview: String? = null,
    var posterPath: String? = null,
    var seasonNumber: Int = 0
): Parcelable