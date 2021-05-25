package com.iswan.main.movflix.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowDetailResponse(

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("episode_run_time")
	val episodeRunTime: List<Int>,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("homepage")
	val homepage: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("last_air_date")
	val lastAirDate: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("number_of_seasons")
	val numberOfSeasons: Int,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItem>,

	@field:SerializedName("seasons")
	val seasons: List<SeasonsItem>,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("vote_count")
	val voteCount: Int

): Parcelable


