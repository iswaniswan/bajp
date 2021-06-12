package com.iswan.main.movflix.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailResponse(

	@field:SerializedName("adult")
	val adult: Boolean,

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("budget")
	val budget: Long,

	@field:SerializedName("genres")
	val genres: List<GenresItem>,

	@field:SerializedName("homepage")
	val homepage: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("original_language")
	val originalLanguage: String,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("poster_path")
	val posterPath: String,

	@field:SerializedName("production_companies")
	val productionCompanies: List<ProductionCompaniesItem>,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("revenue")
	val revenue: Long,

	@field:SerializedName("runtime")
	val runtime: Int,

	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("tagline")
	val tagline: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("vote_count")
	val voteCount: Int

): Parcelable
