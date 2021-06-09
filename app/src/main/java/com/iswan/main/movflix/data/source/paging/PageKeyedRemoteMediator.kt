package com.iswan.main.movflix.data.source.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.iswan.main.movflix.data.source.local.database.MovieDao
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.RemoteKeys
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import com.iswan.main.movflix.data.source.remote.rest.Config
import com.iswan.main.movflix.utils.Mapper
import java.io.InvalidObjectException

@ExperimentalPagingApi
class PageKeyedRemoteMediator(
    private val mDao: MovieDao,
    private val apiService: ApiService
) : RemoteMediator<Int, MovieEntity>() {

    private val TAG = "PageKeyedRemoteMediator"

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieEntity>)
            : MediatorResult {

        return try {
            val page = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKeys = getLastRemoteKey(state)
                    remoteKeys?.nextKey ?: return MediatorResult.Success(true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(true)
                }
                LoadType.REFRESH -> {
                    val remoteKeys = getClosestRemoteKeys(state)
                    remoteKeys?.nextKey?.minus(1) ?: Config.STARTING_PAGE_INDEX
                }
            }

            /* make network request */
            val apiResponse = apiService.getTrendingMovie(page)
            if (apiResponse.results.isNotEmpty()) {
                Log.d(TAG, "load: apiResponse executed")
                val endOfPaginationReached = apiResponse.page == apiResponse.totalPages
                if (loadType == LoadType.REFRESH) {
                    mDao.clearRemoteKeys()
                    mDao.clearMovies()
                }
                val prevKey = if (page == Config.STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val listKeys = apiResponse.results.map {
                    RemoteKeys(movieId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                mDao.insertAll(listKeys)
                val newMovies = apiResponse.results.map {
                    Mapper.responseToEntity(it)
                }
                mDao.insertMovies(newMovies)
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } else {
                Log.d(TAG, "load: apiResponse unexecuted")
                MediatorResult.Success(endOfPaginationReached = true)
            }

        } catch (e: Exception) {
            Log.d(TAG, "exception: ${e.message}")
            MediatorResult.Error(e)
        }
    }

    private suspend fun getClosestRemoteKeys(state: PagingState<Int, MovieEntity>): RemoteKeys? =
        state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                mDao.getRemoteKeys(it.id)
            }
        }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieEntity>): RemoteKeys? =
        state.lastItemOrNull()?.let {
            mDao.getRemoteKeys(it.id)
        }


//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, MovieEntity>
//    ): MediatorResult {
//        try {
//            Log.d(TAG, "load: excuted")
//            when (loadType) {
//                REFRESH -> REQUEST_PAGE
//                PREPEND ->  {
//                    REQUEST_PAGE = if (REQUEST_PAGE > 1) REQUEST_PAGE-- else Config.STARTING_PAGE_INDEX
//                    return MediatorResult.Success(true)
//                }
//                APPEND -> {
//                    REQUEST_PAGE = if (REQUEST_PAGE > 1) REQUEST_PAGE++ else Config.STARTING_PAGE_INDEX
//                    return MediatorResult.Success(endOfPaginationReached = true)
//                }
//            }
//
//            val response = apiService.getTrendingMovie(REQUEST_PAGE)
//            val moviesEntity = response.results.map {
//                Mapper.responseToEntity(it)
//            }
//
//            withContext(dispatcher) {
//                dao.insertMovies(moviesEntity)
//            }
//
//            Log.d(TAG, "REQUEST PAGE now ---> $REQUEST_PAGE")
//            Log.d(TAG, "CHECK -----> response page : ${response.page} ---> total pages : ${response.totalPages}")
//
//            return MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
//        } catch (e: IOException) {
//            Log.d(TAG, "IOException: ${e.message}")
//            return MediatorResult.Error(e)
//        } catch (e: HttpException) {
//            Log.d(TAG, "HttpException: ${e.message}")
//            return MediatorResult.Error(e)
//        }
//    }
}
