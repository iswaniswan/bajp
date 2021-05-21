package com.iswan.main.movflix.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.Movie
import kotlinx.coroutines.launch


class MoviesViewModel(
    private val repository: Repository
): ViewModel() {

    private var _list = MutableLiveData<ArrayList<Movie>>()
    val listMovie : LiveData<ArrayList<Movie>> get () = _list

    private fun getMovies() {
        viewModelScope.launch {
            _list.value = repository.getMovies()
        }
    }

    init {
        getMovies()
    }

}