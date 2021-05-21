package com.iswan.main.movflix.data

import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.models.TvShowDetail

interface IDataSource {

    suspend fun getMovies(): ArrayList<Movie>
    suspend fun getMovie(id: String): MovieDetail

    suspend fun getTvShows(): ArrayList<TvShow>
    suspend fun getTvShow(id: String): TvShowDetail

}