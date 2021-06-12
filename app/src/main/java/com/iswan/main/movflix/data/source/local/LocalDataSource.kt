package com.iswan.main.movflix.data.source.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.source.local.database.MovieDao
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import com.iswan.main.movflix.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: MovieDao
) {

    fun getFavouriteMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dao.getFavouriteMovies() }
        ).flow
            .map { paging ->
                paging.map {
                    Mapper.entityToModel(it)
                }
            }

    fun getFavouriteMovie(id: String): Flow<MovieEntity?> = dao.getFavouriteMovie(id)

    fun insertUpdateFavouriteMovie(movie: MovieEntity) = dao.insertUpdateFavouriteMovie(movie)

    fun getFavouriteTvShow(): Flow<PagingData<TvShow>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dao.getFavouriteTvShows() }
        ).flow
            .map { paging ->
                paging.map {
                    Mapper.entityToModel(it)
                }
            }

    fun getFavouriteTvShow(id: String): Flow<TvShowEntity?> = dao.getFavouriteTvShow(id)

    fun insertUpdateFavouriteTvShow(tvShow: TvShowEntity) = dao.insertUpdateFavouriteTvShow(tvShow)
}
