package com.iswan.main.movflix.data.source

import com.iswan.main.movflix.data.source.remote.response.MovieDetailResponse
import com.iswan.main.movflix.data.source.remote.response.MoviesResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowDetailResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowsResponse

interface IRemoteDataSource {

    suspend fun getMovies(): MoviesResponse

    suspend fun getTvShows(): TvShowsResponse

    suspend fun getMovie(id: String): MovieDetailResponse

    suspend fun getTvShow(id: String): TvShowDetailResponse

}