package com.iswan.main.movflix.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.models.TvShowDetail
import com.iswan.main.movflix.data.source.local.LocalDataSource
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import com.iswan.main.movflix.data.source.paging.MoviePagingSource
import com.iswan.main.movflix.data.source.paging.TvShowPagingSource
import com.iswan.main.movflix.data.source.remote.response.MovieDetailResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowDetailResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.utils.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FakeRepository constructor(
private val localDataSource: LocalDataSource,
private val apiService: ApiService,
private val dispatcher: CoroutineDispatcher,
) {
    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = 5, maxSize = 20, enablePlaceholders = false
    )

    fun getFavouriteMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { localDataSource.getFavouriteMovies() }
        ).flow
            .map { paging ->
                paging.map {
                    Mapper.entityToModel(it)
                }
            }

    fun getMovies(query: String): Flow<PagingData<Movie>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(apiService, query) }
        ).flow


    fun getMovie(id: String): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override fun shouldFetch(data: MovieDetail?): Boolean =
                data == null || data.title.isEmpty()

            override fun loadFromDB(): Flow<MovieDetail?> =
                localDataSource.getFavouriteMovie(id)
                    .map {
                        if (it != null) Mapper.entityToModelDetail(it)
                        else null
                    }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                flow {
                    val response = apiService.getMovie(id)
                    emit(ApiResponse.Success(response))
                }

            override suspend fun saveCallResult(data: MovieDetailResponse) =
                withContext(dispatcher) {
                    val movie = Mapper.responseToEntity(data)
                    localDataSource.insertUpdateFavouriteMovie(movie)
                }
        }.asFlow()

    fun getFavouriteTvShows(): Flow<PagingData<TvShow>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { localDataSource.getFavouriteTvShow() }
        ).flow
            .map { paging ->
                paging.map {
                    Mapper.entityToModel(it)
                }
            }

    fun getTvShows(query: String): Flow<PagingData<TvShow>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { TvShowPagingSource(apiService, query) }
        ).flow

    fun getTvShow(id: String): Flow<Resource<TvShowDetail>> =
        object : NetworkBoundResource<TvShowDetail, TvShowDetailResponse>() {
            override fun shouldFetch(data: TvShowDetail?): Boolean =
                data == null || data.name.isEmpty()

            override fun loadFromDB(): Flow<TvShowDetail?> =
                localDataSource.getFavouriteTvShow(id)
                    .map {
                        if (it != null) Mapper.entityToModelDetail(it)
                        else null
                    }

            override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> {
                return flow {
                    val response = apiService.getTvShow(id)
                    emit(ApiResponse.Success(response))
                }
            }


            override suspend fun saveCallResult(data: TvShowDetailResponse) =
                withContext(dispatcher) {
                    val tvShow = Mapper.responseToEntity(data)
                    localDataSource.insertUpdateFavouriteTvShow(tvShow)
                }
        }.asFlow()

    suspend fun insertUpdateFavouriteMovie(movie: MovieEntity) =
        withContext(dispatcher) {
            localDataSource.insertUpdateFavouriteMovie(movie)
        }

    suspend fun insertUpdateFavouriteTvShow(tvShow: TvShowEntity) =
        withContext(dispatcher) {
            localDataSource.insertUpdateFavouriteTvShow(tvShow)
        }
}