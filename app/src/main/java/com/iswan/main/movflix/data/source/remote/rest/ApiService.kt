package com.iswan.main.movflix.data.source.remote.rest

import com.iswan.main.movflix.BuildConfig
import com.iswan.main.movflix.data.source.remote.response.MovieDetailResponse
import com.iswan.main.movflix.data.source.remote.response.MoviesResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowDetailResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("trending/movie/week")
    suspend fun getTrendingMovie(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MoviesResponse

    @Headers("Accept: application/json", "Content-Type: application/json")
    @GET("trending/tv/week")
    suspend fun getTrendingTvShow(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): TvShowsResponse

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
    ): TvShowsResponse

    @GET("movie/{movieId}")
    suspend fun getMovie(
        @Path("movieId") id: String?,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): MovieDetailResponse

    @GET("tv/{tvId}")
    suspend fun getTvShow(
        @Path("tvId") id: String?,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): TvShowDetailResponse
}