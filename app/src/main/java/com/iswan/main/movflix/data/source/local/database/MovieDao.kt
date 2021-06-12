package com.iswan.main.movflix.data.source.local.database


import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies where isFavourite = 1")
    fun getFavouriteMovies(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdateFavouriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM tvshows where isFavourite = 1")
    fun getFavouriteTvShows(): PagingSource<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpdateFavouriteTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM movies where id=:id")
    fun getFavouriteMovie(id: String): Flow<MovieEntity?>

    @Query("SELECT * FROM tvshows where id=:id")
    fun getFavouriteTvShow(id: String): Flow<TvShowEntity?>
}
