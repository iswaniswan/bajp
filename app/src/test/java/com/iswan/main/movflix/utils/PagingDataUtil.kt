package com.iswan.main.movflix.utils

import androidx.paging.*
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.test.TestCoroutineDispatcher

@Suppress("unchecked_cast")
object PagingDataUtil {

    fun <T: Any> mockData(type: Class<T>) : PagingData<T> {
        val data = when {
            type.isAssignableFrom(Movie::class.java) -> {
                DummyModels.generateMovies() as List<T>
            }
            type.isAssignableFrom(TvShow::class.java) -> {
                DummyModels.generateTvShows() as List<T>
            }
            type.isAssignableFrom(MovieEntity::class.java) -> {
                DummyEntities.generateMovieEntities() as List<T>
            }
            type.isAssignableFrom(TvShowEntity::class.java) -> {
                DummyEntities.generateTvShowEntities() as List<T>
            }
            else -> throw Throwable("unknown class")
        }
        return PagingData.from(data)
    }


    suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) {}
            override fun onInserted(position: Int, count: Int) {}
            override fun onRemoved(position: Int, count: Int) {}
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, TestCoroutineDispatcher()) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                newCombinedLoadStates: CombinedLoadStates,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                onListPresentable()
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }
}