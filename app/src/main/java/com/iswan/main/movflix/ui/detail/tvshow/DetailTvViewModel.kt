package com.iswan.main.movflix.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.models.TvShowDetailEntity
import com.iswan.main.movflix.utils.Injectors.repository
import kotlinx.coroutines.launch

class DetailTvViewModel: ViewModel() {

    private val TAG = "DETAIL TVSHOW VIEWMODEL"

    private var _tvShow = MutableLiveData<TvShowDetailEntity>()
    val tvShow : LiveData<TvShowDetailEntity> get() = _tvShow

    fun getTvShow(id: String) {
        viewModelScope.launch {
            _tvShow.value = repository.getTvShow(id)
        }
    }

}