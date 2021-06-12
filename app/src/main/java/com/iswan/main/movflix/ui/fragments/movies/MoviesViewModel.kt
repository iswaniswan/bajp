package com.iswan.main.movflix.ui.fragments.movies

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var currentQueryValue: String? = null

    private var _movies = MutableLiveData<PagingData<Movie>>()
    val movies: LiveData<PagingData<Movie>> = _movies.cachedIn(viewModelScope)

    fun search(query: String) {
        if (query == currentQueryValue) {
            return
        }

        currentQueryValue = query

        viewModelScope.launch {
            val resultSearch = repository.getMovies(query)
            resultSearch.collect {
                    _movies.value = it
            }
        }
    }

    val favourites : LiveData<PagingData<Movie>> = repository.getFavouriteMovies().asLiveData()
}