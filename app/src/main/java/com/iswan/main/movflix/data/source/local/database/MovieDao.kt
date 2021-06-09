package com.iswan.main.movflix.data.source.local.database


import androidx.paging.PagingSource
import androidx.room.*
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.RemoteKeys

@Dao
interface MovieDao {

//    @Query("SELECT * FROM movies")
//    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE title Like '%' || :query || '%'")
    fun searchMovie(query: String): List<MovieEntity>

    @Query("SELECT * FROM movies")
    fun getAllMovies(): PagingSource<Int, MovieEntity>

//    @Query("SELECT * FROM movies")
//    fun getAllMoviesPaged(): PagingSource<Int, MovieEntity>

//
//    @Query("SELECT * FROM tvshows")
//    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity>
//
//    @Query("SELECT * FROM movies WHERE isFavourite = 1")
//    fun getFavouriteMovies(): DataSource.Factory<Int, MovieEntity>
//
//    @Query("SELECT * FROM tvshows WHERE isFavourite = 1")
//    fun getFavouriteTvShows(): DataSource.Factory<Int, TvShowEntity>
//
//    @Query("SELECT * FROM movies WHERE id = :id")
//    fun getMovie(id: Int): Flow<MovieEntity>
//
//    @Query("SELECT * FROM tvshows WHERE id = :id")
//    fun getTvShow(id: Int): Flow<TvShowEntity>
//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun clearMovies()

    /* Remote Keys */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE movieId = :movieId")
    suspend fun getRemoteKeys(movieId: Int): RemoteKeys

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()


//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertMovie(movie: MovieEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertTvShows(tvshows: List<TvShowEntity>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertTvShow(tvshow: TvShowEntity)
//
//    @Update
//    fun update(movie: MovieEntity)
//
//    @Update
//    fun update(tvshow: TvShowEntity)
//
//    @Query("SELECT COUNT(*) FROM movies")
//    fun countAllMovies(): Int

}
