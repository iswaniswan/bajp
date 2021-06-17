package com.iswan.main.movflix.data.source.local

import androidx.paging.PagingSource
import com.iswan.main.movflix.data.source.local.database.MovieDao
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: MovieDao
) {

    fun getFavouriteMovies(): PagingSource<Int, MovieEntity> = dao.getFavouriteMovies()

    fun getFavouriteMovie(id: String): Flow<MovieEntity?> = dao.getFavouriteMovie(id)

    fun insertUpdateFavouriteMovie(movie: MovieEntity) = dao.insertUpdateFavouriteMovie(movie)

    fun getFavouriteTvShow(): PagingSource<Int, TvShowEntity> = dao.getFavouriteTvShows()

    fun getFavouriteTvShow(id: String): Flow<TvShowEntity?> = dao.getFavouriteTvShow(id)

    fun insertUpdateFavouriteTvShow(tvShow: TvShowEntity) = dao.insertUpdateFavouriteTvShow(tvShow)
}