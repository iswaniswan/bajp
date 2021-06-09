package com.iswan.main.movflix.data.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.iswan.main.movflix.data.source.paging.MoviePagingSource
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = 5, maxSize = 20, enablePlaceholders = false
    )

//    fun getTrendingMovies() =
//        Pager(
//            config = pagingConfig,
//            pagingSourceFactory = { MoviePagingSource(apiService, null) }
//        ).liveData

    fun searchMovies(query: String) =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(apiService, query) }
        ).flow

//    fun searchMoviesL(query: String) =
//        Pager(
//            config = pagingConfig,
//            pagingSourceFactory = { MoviePagingSource(apiService, query) }
//        ).liveData
//
//    suspend fun getMovieById(id: String) = apiService.getMovie(id)

}

