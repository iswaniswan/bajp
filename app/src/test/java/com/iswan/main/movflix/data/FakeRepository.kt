package com.iswan.main.movflix.data

import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.utils.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//class FakeRepository constructor(
//    private val remoteDataSource: RemoteDataSource
//) : IDataSource {
//
//    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
//
//    override suspend fun getMovies(): ArrayList<Movie> =
//        withContext(defaultDispatcher) {
//            val list = ArrayList<Movie>()
//            val results = remoteDataSource.getMovies().results
//            if (results.isNotEmpty()) {
//                results.map {
//                    list.add(Mapper.responseToModel(it))
//                }
//            }
//            return@withContext list
//        }
//
//    override suspend fun getMovie(id: String): MovieDetail =
//        withContext(defaultDispatcher) {
//            val request = remoteDataSource.getMovie(id)
//            return@withContext Mapper.responseToModel(request)
//        }
//
//    override suspend fun getTvShows(): ArrayList<TvShow> =
//        withContext(defaultDispatcher) {
//            val list = ArrayList<TvShow>()
//            val results = remoteDataSource.getTvShows().results
//            if (results.isNotEmpty()) {
//                results.map {
//                    list.add(Mapper.responseToModel(it))
//                }
//            }
//            return@withContext list
//        }
//
//    override suspend fun getTvShow(id: String): TvShowDetail =
//        withContext(defaultDispatcher) {
//            val request = remoteDataSource.getTvShow(id)
//            return@withContext Mapper.responseToModel(request)
//        }
//}