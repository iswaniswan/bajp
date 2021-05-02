package com.iswan.main.movflix.data

import com.iswan.main.movflix.data.models.MovieDetailEntity
import com.iswan.main.movflix.data.models.MovieEntity
import com.iswan.main.movflix.data.models.TvShowDetailEntity
import com.iswan.main.movflix.data.models.TvShowEntity
import com.iswan.main.movflix.data.rest.*
import com.iswan.main.movflix.utils.Utils
import kotlinx.coroutines.*

class Repository(private val apiMovies: ApiMovies) {

    private val utils: Utils = Utils()
    private val TAG = "REPOSITORY -------->"
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

    suspend fun getMovies() : ArrayList<MovieEntity> =
        withContext(defaultDispatcher) {
            val list =  ArrayList<MovieEntity>()
            val response = apiMovies.getTrendingMoviesO()
            if (response.results.size() > 0) {
                list.addAll(utils.arrToMovieList(response.results))
            }
            return@withContext list
        }

    suspend fun getTvShows(): ArrayList<TvShowEntity> =
        withContext(defaultDispatcher) {
            val list = ArrayList<TvShowEntity>()
            val response = apiMovies.getTrendingTvShowsO()
            if (response.results.size() > 0) {
                list.addAll(utils.arrToTvList(response.results))
            }
            return@withContext list
        }

    suspend fun getMovie(id: String) : MovieDetailEntity =
        withContext(defaultDispatcher) {
            val response = apiMovies.getMovieO(id)
            return@withContext utils.jsonToMovieDetail(response)
        }

    suspend fun getTvShow(id: String) : TvShowDetailEntity =
        withContext(defaultDispatcher) {
            val response = apiMovies.getTvShowO(id)
            return@withContext utils.jsonToTvShowDetail(response)
        }


}

