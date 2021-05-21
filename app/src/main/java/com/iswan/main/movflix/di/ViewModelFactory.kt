package com.iswan.main.movflix.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.ui.detail.movie.DetailMovieViewModel
import com.iswan.main.movflix.ui.detail.tvshow.DetailTvViewModel
import com.iswan.main.movflix.ui.main.movies.MoviesViewModel
import com.iswan.main.movflix.ui.main.tvshows.TvShowsViewModel

class ViewModelFactory constructor(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                return MoviesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                return TvShowsViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                return DetailMovieViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                return DetailTvViewModel(repository) as T
            }
            else -> throw Throwable("unknow class : " + modelClass.name)
        }
    }

}