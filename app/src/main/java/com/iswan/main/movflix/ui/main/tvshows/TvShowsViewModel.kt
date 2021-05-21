package com.iswan.main.movflix.ui.main.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.TvShow
import kotlinx.coroutines.launch


class TvShowsViewModel constructor(
    private val repository: Repository
): ViewModel() {

    private var _list = MutableLiveData<ArrayList<TvShow>>()
    val listMovie : LiveData<ArrayList<TvShow>> get () = _list

    private fun getTvs() {
        viewModelScope.launch {
            _list.value = repository.getTvShows()
        }
    }

    init {
        getTvs()
    }

}