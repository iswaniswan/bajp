package com.iswan.main.movflix.ui.main.movies

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.utils.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository,
    state: SavedStateHandle
): ViewModel() {

    private val TAG = "MoviesViewModel"

    companion object {
        private const val CURRENT_QUERY = "curent_query"
        private const val EMPTY_QUERY = ""
    }

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Movie>>? = null

    fun search(query: String): Flow<PagingData<Movie>> {
        val lastResult = currentSearchResult

        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }

        currentQueryValue = query

        val newResult: Flow<PagingData<Movie>> =
            if (query.isNotEmpty()) {
                repository.searchMovies(query)
            } else {
                repository.getTrendingMovies()
                    .cachedIn(viewModelScope)
            }

        currentSearchResult = newResult
        return newResult
    }

    /*  */
//    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)

//    private var _movies = MutableLiveData<PagingData<Movie>>()
//        .apply {
//        viewModelScope.launch {
//            /* using live data */
//            repository.getMoviesL().map {
//                value = it.map {
//                    Mapper.entityToModel(it)
//                }
//            }


            /* using flow */
//            repository.getMovies()
//                .collectLatest {
//                    value = it.map {
//                        Mapper.entityToModel(it)
//                    }
//            }
//        }
//    }

//    val movies = currentQuery.switchMap { query ->
//        if (!query.isEmpty()) {
//            repository.searchMoviesL(query)
//        } else {
//            /* using live data */
//            repository.getMoviesL().map {
//                it.map {
//                    Mapper.entityToModel(it)
//                }
//            }.cachedIn(viewModelScope)
//        }
//    }
//
//    fun search(query: String) {
//        currentQuery.value = query
//    }

}