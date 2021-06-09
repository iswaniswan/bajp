package com.iswan.main.movflix.ui.main.tvshows

import androidx.lifecycle.ViewModel
import com.iswan.main.movflix.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

//    private var _list = MutableLiveData<ArrayList<TvShow>>()
//    val listMovie : LiveData<ArrayList<TvShow>> get () = _list
//
//    private fun getTvs() {
//        viewModelScope.launch {
//            _list.value = repository.getTvShows()
//        }
//    }
//
//    init {
//        getTvs()
//    }

}