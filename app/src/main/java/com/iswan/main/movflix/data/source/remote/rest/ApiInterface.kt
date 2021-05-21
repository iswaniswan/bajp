package com.iswan.main.movflix.data.source.remote.rest

import com.iswan.main.movflix.data.source.remote.response.MovieDetailResponse
import com.iswan.main.movflix.data.source.remote.response.MoviesResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowDetailResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("trending/movie/week")
    suspend fun trendingMovieO(@Query("api_key") apiKey: String): MoviesResponse

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("trending/tv/week")
    suspend fun trendingTvShowO(@Query("api_key") apiKey: String): TvShowsResponse

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") id: String?,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse

    @GET("tv/{tvId}")
    suspend fun getTvShow(
        @Path("tvId") id: String?,
        @Query("api_key") apiKey: String
    ): TvShowDetailResponse

}