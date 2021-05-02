package com.iswan.main.movflix.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.models.MovieDetailEntity
import com.iswan.main.movflix.utils.Injectors.repository
import kotlinx.coroutines.launch

class DetailMovieViewModel: ViewModel() {

    private val TAG = "DETAIL MOVIE VIEWMODEL"

    private var _movie = MutableLiveData<MovieDetailEntity>()
    val movie : LiveData<MovieDetailEntity> get() = _movie

    fun getMovie(id: String) {
        viewModelScope.launch {
            _movie.value = repository.getMovie(id)
        }
    }

}