package com.iswan.main.movflix.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.source.paging.MoviePagingSource
import com.iswan.main.movflix.data.source.paging.TvShowPagingSource
import com.iswan.main.movflix.data.source.remote.response.MovieDetailResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowDetailResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = 5, maxSize = 20, enablePlaceholders = false
    )

    fun getMovies(query: String): Flow<PagingData<Movie>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(apiService, query) }
        ).flow

    suspend fun getMovie(id: String): Flow<ApiResponse<MovieDetailResponse>> = flow {
        val response = apiService.getMovie(id)
        emit(ApiResponse.Success(response))
    }

    fun getTvShows(query: String): Flow<PagingData<TvShow>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { TvShowPagingSource(apiService, query) }
        ).flow

    suspend fun getTvShow(id: String): Flow<ApiResponse<TvShowDetailResponse>> = flow {
        val response = apiService.getTvShow(id)
        emit(ApiResponse.Success(response))
    }

}

