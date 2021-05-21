package com.iswan.main.movflix.data.source.remote.rest

import com.iswan.main.movflix.BuildConfig

class ApiMovies(private val apiClient: ApiClient) {

    suspend fun getTrendingMoviesO() = apiClient.instance().trendingMovieO(BuildConfig.TMDB_API_KEY)

    suspend fun getTrendingTvShowsO() = apiClient.instance().trendingTvShowO(BuildConfig.TMDB_API_KEY)

    suspend fun getMovieO(id: String) = apiClient.instance().getMovie(id, BuildConfig.TMDB_API_KEY)

    suspend fun getTvShowO(id: String) = apiClient.instance().getTvShow(id, BuildConfig.TMDB_API_KEY)

}