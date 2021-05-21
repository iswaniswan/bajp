package com.iswan.main.movflix.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.MovieDetail
import kotlinx.coroutines.launch

class DetailMovieViewModel constructor(
    private val repository: Repository
): ViewModel() {

    private var _movie = MutableLiveData<MovieDetail>()
    val movie : LiveData<MovieDetail> get() = _movie

    fun getMovie(id: String) {
        viewModelScope.launch {
            _movie.value = repository.getMovie(id)
        }
    }

}