package com.iswan.main.movflix.data

import android.util.Log
import androidx.paging.PagingData
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.models.TvShowDetail
import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.data.source.local.LocalDataSource
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import com.iswan.main.movflix.data.source.remote.response.MovieDetailResponse
import com.iswan.main.movflix.data.source.remote.response.TvShowDetailResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiResponse
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.utils.Mapper
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher,
) {

    fun getFavouriteMovies(): Flow<PagingData<Movie>> =
        localDataSource.getFavouriteMovies()

    fun getMovies(query: String): Flow<PagingData<Movie>> =
        remoteDataSource.getMovies(query)

    fun getMovie(id: String): Flow<Resource<MovieDetail>> =
        object : NetworkBoundResource<MovieDetail, MovieDetailResponse>() {
            override fun shouldFetch(data: MovieDetail?): Boolean =
                data == null || data?.title.isEmpty()

            override fun loadFromDB(): Flow<MovieDetail?> {
                return localDataSource.getFavouriteMovie(id).map {
                    if (it != null) {
                        Mapper.entityToModelDetail(it)
                    } else {
                        null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovie(id)

            override suspend fun saveCallResult(data: MovieDetailResponse) =
                withContext(dispatcher) {
                    val movie = Mapper.responseToEntity(data)
                    localDataSource.insertUpdateFavouriteMovie(movie)
                }

        }.asFlow()

    fun getFavouriteTvShows(): Flow<PagingData<TvShow>> =
        localDataSource.getFavouriteTvShow()

    fun getTvShows(query: String): Flow<PagingData<TvShow>> =
        remoteDataSource.getTvShows(query)

    fun getTvShow(id: String): Flow<Resource<TvShowDetail>> =
        object : NetworkBoundResource<TvShowDetail, TvShowDetailResponse>() {
            override fun shouldFetch(data: TvShowDetail?): Boolean =
                data == null || data?.name.isEmpty()

            override fun loadFromDB(): Flow<TvShowDetail?> {
                return localDataSource.getFavouriteTvShow(id).map {
                    if (it != null) {
                        Mapper.entityToModelDetail(it)
                    } else {
                        null
                    }
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<TvShowDetailResponse>> =
                remoteDataSource.getTvShow(id)

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