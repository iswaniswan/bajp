package com.iswan.main.movflix.data

import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.models.TvShowDetail
import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.utils.IdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository constructor(
    private val remoteDataSource: RemoteDataSource
) : IDataSource {

    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(rdSource: RemoteDataSource): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(rdSource).apply { instance = this }
            }
    }

    override suspend fun getMovies(): ArrayList<Movie> =
        withContext(defaultDispatcher) {
            /* TODO remove idling later */
            IdlingResource.idling.increment()
            val list = ArrayList<Movie>()
            val results = remoteDataSource.getMovies().results
            if (results.isNotEmpty()) {
                results.map {
                    list.add(Mapper.responseToModel(it))
                }
                IdlingResource.idling.decrement()
            }
            return@withContext list
        }

    override suspend fun getMovie(id: String): MovieDetail =
        withContext(defaultDispatcher) {
            /* TODO remove idling later */
            IdlingResource.idling.increment()
            val request = remoteDataSource.getMovie(id)
            IdlingResource.idling.decrement()
            return@withContext Mapper.responseToModel(request)
        }

    override suspend fun getTvShows(): ArrayList<TvShow> =
        withContext(defaultDispatcher) {
            /* TODO remove idling later */
            IdlingResource.idling.increment()
            val list = ArrayList<TvShow>()
            val results = remoteDataSource.getTvShows().results
            if (results.isNotEmpty()) {
                results.map {
                    list.add(Mapper.responseToModel(it))
                }
                IdlingResource.idling.decrement()
            }
            return@withContext list
        }

    override suspend fun getTvShow(id: String): TvShowDetail =
        withContext(defaultDispatcher) {
            /* TODO remove idling later */
            IdlingResource.idling.increment()
            val request = remoteDataSource.getTvShow(id)
            IdlingResource.idling.decrement()
            return@withContext Mapper.responseToModel(request)
        }
}