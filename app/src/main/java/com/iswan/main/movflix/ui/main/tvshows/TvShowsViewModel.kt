package com.iswan.main.movflix.ui.main.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.models.TvShowEntity
import com.iswan.main.movflix.utils.Injectors.repository
import kotlinx.coroutines.launch


class TvShowsViewModel: ViewModel() {

    private var _list = MutableLiveData<ArrayList<TvShowEntity>>()
    val listMovie : LiveData<ArrayList<TvShowEntity>> get () = _list

    private fun initCo() {
        viewModelScope.launch {
            _list.value = repository.getTvShows()
        }
    }

    init {
        initCo()
    }

}