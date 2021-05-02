package com.iswan.main.movflix.data.rest

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("trending/movie/week")
    suspend fun trendingMovieO(@Query("api_key") apiKey: String): ResponseArrayObject

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("trending/tv/week")
    suspend fun trendingTvShowO(@Query("api_key") apiKey: String): ResponseArrayObject

    @GET("movie/{movieId}")
    suspend fun getMovie(@Path("movieId") id: String?,@Query("api_key") apiKey: String): JsonObject

    @GET("tv/{tvId}")
    suspend fun getTvShow(@Path("tvId") id: String?,@Query("api_key") apiKey: String): JsonObject

}