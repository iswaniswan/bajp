package com.iswan.main.movflix.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<MovieResponse>,

	@field:SerializedName("total_results")
	val totalResults: Int

): Parcelable

@Parcelize
data class MovieResponse(

	@field:SerializedName("id")
	var id: Int,

	@field:SerializedName("poster_path")
	var poster_path: String?,

	@field:SerializedName("overview")
	var overview: String?,

	@field:SerializedName("release_date")
	var release_date: String?,

	@field:SerializedName("title")
	var title: String?,

	@field:SerializedName("vote_average")
	var vote_average: Double

) : Parcelable
