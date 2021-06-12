package com.iswan.main.movflix.ui.fragments.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<TvShow>>? = null

    fun search(query: String): Flow<PagingData<TvShow>> {
        val lastResult = currentSearchResult

        if (query == currentQueryValue && lastResult != null) {
            return lastResult
        }

        currentQueryValue = query

        val newResult: Flow<PagingData<TvShow>> =
            repository.getTvShows(query).cachedIn(viewModelScope)

        currentSearchResult = newResult
        return newResult
    }

    fun getFavourites(): Flow<PagingData<TvShow>> = repository.getFavouriteTvShows()

}