package com.iswan.main.movflix.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTvViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

//    private var _tvShow = MutableLiveData<TvShowDetail>()
//    val tvShow: LiveData<TvShowDetail> get() = _tvShow
//
//    fun getTvShow(id: String) {
//        viewModelScope.launch {
//            _tvShow.value = repository.getTvShow(id)
//        }
//    }

}