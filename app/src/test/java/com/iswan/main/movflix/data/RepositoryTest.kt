package com.iswan.main.movflix.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.data.source.RemoteDataSource
import com.iswan.main.movflix.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    private val repository = FakeRepository(remoteDataSource)

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
    fun getMovies() = runBlocking {
        Mockito.`when`(remoteDataSource.getMovies())
            .thenReturn(responseMovies)
        val results = repository.getMovies()
        Mockito.verify(remoteDataSource).getMovies()
        assertNotNull(results)
        assertEquals(20, results.size)
        assertEquals(dummyMovies[0].title, results[0].title)
    }

    @Test
    fun getMovie() = runBlocking {
        Mockito.`when`(remoteDataSource.getMovie(any()))
            .thenReturn(responseMovie)
        val result = repository.getMovie(dummyMovieId)
        Mockito.verify(remoteDataSource).getMovie(dummyMovieId)
        assertNotNull(result)
        assertEquals(dummyMovieDetail, result)
    }


    @Test
    fun getTvShows() = runBlocking {
        Mockito.`when`(remoteDataSource.getTvShows())
            .thenReturn(responseTvShows)
        val results = repository.getTvShows()
        Mockito.verify(remoteDataSource).getTvShows()
        assertNotNull(results)
        assertEquals(20, results.size)
        assertEquals(dummyTvShows[0].name, results[0].name)
    }

    @Test
    fun getTvShow() = runBlocking {
        Mockito.`when`(remoteDataSource.getTvShow(any()))
            .thenReturn(responseTvShow)
        val tvShow = repository.getTvShow(dummyTvShowId)
        Mockito.verify(remoteDataSource).getTvShow(dummyTvShowId)
        assertNotNull(tvShow)
        assertEquals(dummyTvShowDetail.name, tvShow.name)
    }

}