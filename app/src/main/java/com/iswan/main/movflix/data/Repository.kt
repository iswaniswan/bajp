package com.iswan.main.movflix.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.*
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.data.source.local.LocalDataSource
import com.iswan.main.movflix.data.source.local.database.MovieDao
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.paging.MoviePagingSource
import com.iswan.main.movflix.data.source.paging.PageKeyedRemoteMediator
import com.iswan.main.movflix.data.source.paging.PagingRepository
import com.iswan.main.movflix.data.source.remote.response.MoviesResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val apiService: ApiService,
    private val dao: MovieDao
) {

    private val TAG = "REPOSITORY"

    private val pagingConfig = PagingConfig(
        pageSize = 5,
        maxSize = 20,
        enablePlaceholders = false,
    )

    fun searchMovies(query: String) =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(apiService, query) }
        ).flow

    fun getTrendingMovies() =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = { MoviePagingSource(apiService, null) }
        ).flow

    @OptIn(ExperimentalPagingApi::class)
    fun getMovies(): Flow<PagingData<MovieEntity>> =
        Pager(
            config = pagingConfig,
            remoteMediator = PageKeyedRemoteMediator(dao, apiService),
            pagingSourceFactory = { dao.getAllMovies() }
        ).flow



//    suspend fun addFavouriteMovie(movieT: MovieT) = movieDao.addFavouriteMovie(movieT)
//    suspend fun isMovieExists(id: String) = movieDao.isMovieExists(id)
//    suspend fun remFavouriteMovie(id: String) = movieDao.remFavouriteMovie(id)
//    suspend fun getMovies() = movieDao.getMovies()


//    suspend fun getMovieApi(id: String) = remoteDataSource.getMovieById(id)
//    fun getTrendingMovies() = remoteDataSource.getTrendingMovies()

    /* using live data */
//    fun searchMoviesL(query: String) = remoteDataSource.searchMoviesL(query)



//    @OptIn(ExperimentalPagingApi::class)
//    fun getMoviesL(): LiveData<PagingData<MovieEntity>> =
//        Pager(
//            config = PagingConfig(
//                pageSize = 5, maxSize = 20, enablePlaceholders = false
//            ),
//            remoteMediator = PageKeyedRemoteMediator(dao, apiService),
//        ){
//            dao.getAllMoviesPaged()
//        }.liveData


//    suspend fun getTvShowsById(id: String) = movieRep.getTvShowsById(id)
//    fun getTrendingTvShows() = movieRep.getTrendingTvShows()
//    fun searchTvShows(query: String) = movieRep.searchTvShows(query)

//    suspend fun getMovies(): ArrayList<Movie> =
//        withContext(dispatcher) {
//            /* TODO remove idling later */
//            IdlingResource.idling.increment()
//            val list = ArrayList<Movie>()
//            val results = remoteDataSource.getMovies().results
//            if (results.isNotEmpty()) {
//                results.map {
//                    list.add(Mapper.responseToModel(it))
//                }
//                IdlingResource.idling.decrement()
//            }
//            return@withContext list
//        }
//
//    suspend fun getMovie(id: String): MovieDetail =
//        withContext(dispatcher) {
//            /* TODO remove idling later */
//            IdlingResource.idling.increment()
//            val request = remoteDataSource.getMovie(id)
//            IdlingResource.idling.decrement()
//            return@withContext Mapper.responseToModel(request)
//        }
//
//    suspend fun getTvShows(): ArrayList<TvShow> =
//        withContext(dispatcher) {
//            /* TODO remove idling later */
//            IdlingResource.idling.increment()
//            val list = ArrayList<TvShow>()
//            val results = remoteDataSource.getTvShows().results
//            if (results.isNotEmpty()) {
//                results.map {
//                    list.add(Mapper.responseToModel(it))
//                }
//                IdlingResource.idling.decrement()
//            }
//            return@withContext list
//        }
//
//    suspend fun getTvShow(id: String): TvShowDetail =
//        withContext(dispatcher) {
//            /* TODO remove idling later */
//            IdlingResource.idling.increment()
//            val request = remoteDataSource.getTvShow(id)
//            IdlingResource.idling.decrement()
//            return@withContext Mapper.responseToModel(request)
//        }
}