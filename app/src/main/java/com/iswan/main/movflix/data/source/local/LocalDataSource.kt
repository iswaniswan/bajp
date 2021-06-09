package com.iswan.main.movflix.data.source.local

import androidx.paging.*
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.source.local.database.MovieDao
import com.iswan.main.movflix.utils.Mapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dao: MovieDao
) {

//    private val pagingConfig: PagingConfig = PagingConfig(
//        pageSize = 5, maxSize = 20, enablePlaceholders = false
//    )

    //    fun getMovie(id: Int): Flow<MovieEntity> = dao.getMovie(id)
//
//    fun getAllMovies(): PagingSource<Int, Movie> {
//        val pager = Pager(
//            config = pagingConfig,
//            pagingSourceFactory = { MovieDBPagingSource(dao, null) }
//        )
//    }

//
//    fun searchMovies(query: String) =
//        Pager(
//            config = pagingConfig,
//            pagingSourceFactory = { MovieDBPagingSource(dao, query) }
//        ).liveData


//
//    fun countAllMovies(): Int = dao.countAllMovies()
//
//    fun getTvShow(id: Int): Flow<TvShowEntity> = dao.getTvShow(id)
//
//    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = dao.getAllTvShows()
//
//    fun getFavouriteMovies(): DataSource.Factory<Int, MovieEntity> = dao.getFavouriteMovies()
//
//    fun getFavouriteTvShows(): DataSource.Factory<Int, TvShowEntity> = dao.getFavouriteTvShows()
//
//    fun insertMovies(movies: List<MovieEntity>) = dao.insertMovies(movies)
//
//    fun insertMovie(movie: MovieEntity) = dao.insertMovie(movie)
//
//    fun insertTvShows(tvshows: List<TvShowEntity>) = dao.insertTvShows(tvshows)
//
//    fun insertTvShow(tvshow: TvShowEntity) = dao.insertTvShow(tvshow)
//
//    fun update(movie: MovieEntity) = dao.update(movie)
//
//    fun update(tvshow: TvShowEntity) = dao.update(tvshow)

}