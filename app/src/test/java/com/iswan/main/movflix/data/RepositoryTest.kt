package com.iswan.main.movflix.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.models.TvShow
import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class RepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)

    private val responseMovie = FakeResponse.generateResponseMovie()
    private val responseMovies = FakeResponse.generateResponseMovies()
    private val responseTvShow = FakeResponse.generateResponseTvShow()
    private val responseTvShows = FakeResponse.generateResponseTvShows()

    private val dummyMovies = FakeData.generateMovies()
    private val dummyMovieDetail = FakeData.generateMovieDetail()
    private val dummyMovieId = dummyMovieDetail.id.toString()
    private val dummyTvShows = FakeData.generateTvShows()
    private val dummyTvShowDetail = FakeData.generateTvShowDetail()
    private val dummyTvShowId = dummyTvShowDetail.id.toString()

    @Test
    fun getMovies() = runBlockingTest {
        Mockito.`when`(remoteDataSource.getMovies()).thenReturn(responseMovies)
        val results = remoteDataSource.getMovies().results
        Mockito.verify(remoteDataSource).getMovies()
        assertNotNull(results)
        assertEquals(20, results.size)
        val movies = ArrayList<Movie>().apply {
            results.map {
                this.add(Mapper.responseToModel(it))
            }
        }
        assertEquals(dummyMovies, movies)
    }

    @Test
    fun getMovie() = runBlockingTest {
        Mockito.`when`(remoteDataSource.getMovie(dummyMovieId)).thenReturn(responseMovie)
        val result = remoteDataSource.getMovie(dummyMovieId)
        Mockito.verify(remoteDataSource).getMovie(dummyMovieId)
        assertNotNull(result)
        val movieDetail = Mapper.responseToModel(result)
        assertEquals(dummyMovieDetail, movieDetail)
    }

    @Test
    fun getTvShows() = runBlockingTest {
        Mockito.`when`(remoteDataSource.getTvShows()).thenReturn(responseTvShows)
        val results = remoteDataSource.getTvShows().results
        Mockito.verify(remoteDataSource).getTvShows()
        assertNotNull(results)
        assertEquals(20, results.size)
        val tvShows = ArrayList<TvShow>().apply {
            results.map {
                this.add(Mapper.responseToModel(it))
            }
        }
        assertEquals(dummyTvShows, tvShows)
    }

    @Test
    fun getTvShow() = runBlockingTest {
        Mockito.`when`(remoteDataSource.getTvShow(dummyTvShowId)).thenReturn(responseTvShow)
        val result = remoteDataSource.getTvShow(dummyTvShowId)
        Mockito.verify(remoteDataSource).getTvShow(dummyTvShowId)
        assertNotNull(result)
        val tvShowDetail = Mapper.responseToModel(result)
        assertEquals(dummyTvShowDetail, tvShowDetail)
    }
}