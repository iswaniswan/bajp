package com.iswan.main.movflix.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TvShowsResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TvShowResponse>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

@Parcelize
data class TvShowResponse(

	@field:SerializedName("id")
	var id: Int,

	@field:SerializedName("poster_path")
	var posterPath: String?,

	@field:SerializedName("overview")
	var overview: String?,

	@field:SerializedName("first_air_date")
	var firstAirDate: String?,

	@field:SerializedName("name")
	var name: String?,

	@field:SerializedName("vote_average")
	var voteAverage: Double,
) : Parcelable

