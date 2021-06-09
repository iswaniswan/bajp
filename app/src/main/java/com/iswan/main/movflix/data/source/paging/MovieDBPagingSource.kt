package com.iswan.main.movflix.data.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iswan.main.movflix.utils.Mapper
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.source.local.database.MovieDao
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import com.iswan.main.movflix.data.source.remote.rest.Config
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.internal.wait
import retrofit2.HttpException
import java.io.IOException

//class MovieDBPagingSource(
//    private val dao: MovieDao,
//    private val query: String?
//): PagingSource<Int, Movie>() {
//
//    private val TAG = "MOVIE PAGING SOURCE"
//
//    private var STATE_PAGE: Int = Config.STARTING_PAGE_INDEX
//
//    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
//        return try {
//            Log.d(TAG, "load: getRefreshKey ")
//            Log.d(TAG, "load: params key : ${params.key?.toInt()}")
//            Log.d(TAG, "load: state page : $STATE_PAGE")
//
//            with(dispatcher) {
//                Log.d(TAG, "load response: executed")
//                val position = params.key ?: Config.STARTING_PAGE_INDEX
//                val response = if (query != null) dao.searchMovie(query)
//                else dao.getAllMovies()
//
//                val movies = response.map {
//                    Mapper.entityToModel(it)
//                }
//                LoadResult.Page(
//                    data = movies,
//                    prevKey = if (STATE_PAGE > 1) STATE_PAGE.minus(1) else Config.STARTING_PAGE_INDEX,
//                    nextKey = if (STATE_PAGE > 1) STATE_PAGE.plus(1) else Config.STARTING_PAGE_INDEX
//                )
//            }
//        } catch (e: IOException) {
//            Log.d(TAG, "load: IOEXCEPTION -> $e")
//            LoadResult.Error(e)
//        } catch (e: HttpException) {
//            Log.d(TAG, "load: HTTPEXCEPTION -> $e")
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
//                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
//        }
//    }
//
//}