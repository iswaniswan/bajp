package com.iswan.main.movflix.ui.main.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.models.MovieEntity
import com.iswan.main.movflix.utils.Injectors.repository
import kotlinx.coroutines.launch


class MoviesViewModel: ViewModel() {

    private var _list = MutableLiveData<ArrayList<MovieEntity>>()
    val listMovie : LiveData<ArrayList<MovieEntity>> get () = _list

    private fun getMovies() {
        viewModelScope.launch {
            _list.value = repository.getMovies()
        }
    }

    init {
        getMovies()
    }

}