package com.iswan.main.movflix.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class SeasonsItem(

    @field:SerializedName("air_date")
    val airDate: String,

    @field:SerializedName("episode_count")
    val episodeCount: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("season_number")
    val seasonNumber: Int

): Parcelable

@Parcelize
data class ProductionCompaniesItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("logo_path")
    val logoPath: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("origin_country")
    val originCountry: String

): Parcelable

@Parcelize
data class GenresItem(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String

): Parcelable