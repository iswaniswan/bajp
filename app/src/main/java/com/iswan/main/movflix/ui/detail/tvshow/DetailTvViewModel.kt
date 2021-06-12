package com.iswan.main.movflix.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.data.models.TvShowDetail
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.utils.Mapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailTvViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _tvShow = MutableLiveData<Resource<TvShowDetail>>(Resource.Loading())

    val tvShow : LiveData<Resource<TvShowDetail>> = _tvShow

    fun getTvShow(id: String) =
        viewModelScope.launch {
            withContext(dispatcher) {
                val data = repository.getTvShow(id)
                data.collect {
                    _tvShow.postValue(it)
                }
            }
        }

    fun insertUpdateFavourite(movie: TvShowDetail) =
        viewModelScope.launch {
            val tvShowEntity = Mapper.modelToEntity(movie)
            tvShowEntity.isFavourite = !tvShowEntity.isFavourite
            repository.insertUpdateFavouriteTvShow(tvShowEntity)
        }
}