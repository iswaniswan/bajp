package com.iswan.main.movflix.data.rest

import com.google.gson.JsonArray
import com.iswan.main.movflix.data.models.MovieEntity
import com.iswan.main.movflix.data.models.TvShowEntity
import com.squareup.moshi.Json


class ResponseArrayObject (

    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val results: JsonArray,

    @Json(name = "total_pages")
    val totalPages: Int,

    @Json(name = "total_results")
    val totalResults: Int,

) {
    fun getListMovie() : List<MovieEntity> =
        results.map {
            it.asMovie
        }

    fun getListTvShow(): List<TvShowEntity> =
        results.map {
            it.asTvShow
        }
}


