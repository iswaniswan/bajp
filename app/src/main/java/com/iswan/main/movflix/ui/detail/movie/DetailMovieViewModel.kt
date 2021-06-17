package com.iswan.main.movflix.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.utils.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private var _movie = MutableLiveData<Resource<MovieDetail>>(Resource.Loading())
    val movie : LiveData<Resource<MovieDetail>> = _movie

    fun getMovie(id: String) =
        viewModelScope.launch {
            withContext(dispatcher) {
                val data = repository.getMovie(id)
                data.collect {
                    _movie.postValue(it)
                }
            }
        }

    fun insertUpdateFavourite(movie: MovieDetail) =
        viewModelScope.launch {
            val movieEntity = Mapper.modelToEntity(movie)
            movieEntity.isFavourite = !movieEntity.isFavourite
            repository.insertUpdateFavouriteMovie(movieEntity)
        }
}