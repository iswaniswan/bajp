package com.iswan.main.movflix.ui.fragments.tvshows

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.TvShow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private var currentQueryValue: String? = null

    private var _tvShows = MutableLiveData<PagingData<TvShow>>()
    val tvShows: LiveData<PagingData<TvShow>> = _tvShows.cachedIn(viewModelScope)

    fun search(query: String) {
        if (query == currentQueryValue) {
            return
        }

        currentQueryValue = query

        viewModelScope.launch {
            val resultSearch = repository.getTvShows(query)
            resultSearch.collect {
                _tvShows.value = it
            }
        }
    }

    val favourites : LiveData<PagingData<TvShow>> = repository.getFavouriteTvShows().asLiveData()
}