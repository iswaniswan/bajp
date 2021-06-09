package com.iswan.main.movflix.data.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.iswan.main.movflix.utils.Mapper
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import com.iswan.main.movflix.data.source.remote.rest.Config
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val apiService: ApiService,
    private val query: String?
): PagingSource<Int, Movie>() {

    private val TAG = "MOVIE PAGING SOURCE"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: Config.STARTING_PAGE_INDEX
            val response = if (query != null) apiService.searchMovies(query, position)
            else apiService.getTrendingMovie(position)

            val movies = response.results.map {
                Mapper.responseToModel(it)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (position == Config.STARTING_PAGE_INDEX) null else position -1,
                nextKey = if (movies.isEmpty()) null else position +1
            )
        } catch (e: IOException) {
            Log.d(TAG, "load: IOEXCEPTION -> $e")
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Log.d(TAG, "load: HTTPEXCEPTION -> $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}