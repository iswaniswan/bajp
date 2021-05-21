package com.iswan.main.movflix.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.data.FakeResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiMovies
import com.iswan.main.movflix.utils.DataDummy
import com.iswan.main.movflix.utils.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var apiMovies: ApiMovies

    private val movieId = DataDummy.getSampleMovieDetail().id.toString()
    private val tvShowId = DataDummy.getSampleTvShowDetail().id.toString()

    private val responseMovie = FakeResponse.generateResponseMovie()
    private val responseMovies = FakeResponse.generateResponseMovies()
    private val responseTvShow = FakeResponse.generateResponseTvShow()
    private val responseTvShows = FakeResponse.generateResponseTvShows()

    @Test
    fun getMovies() = runBlockingTest {
        Mockito.`when`(apiMovies.getTrendingMoviesO()).thenReturn(responseMovies)
        val request = apiMovies.getTrendingMoviesO()
        Mockito.verify(apiMovies).getTrendingMoviesO()
        assertNotNull(request)
        assertEquals(responseMovies, request)
        assertEquals(20, request.results.size)
    }

    @Test
    fun getTvShows() = runBlockingTest {
        Mockito.`when`(apiMovies.getTrendingTvShowsO()).thenReturn(responseTvShows)
        val request = apiMovies.getTrendingTvShowsO()
        Mockito.verify(apiMovies).getTrendingTvShowsO()
        assertNotNull(request)
        assertEquals(responseTvShows, request)
        assertEquals(20, request.results.size)
    }

    @Test
    fun getMovie() = runBlockingTest {
        Mockito.`when`(apiMovies.getMovieO(any())).thenReturn(responseMovie)
        val request = apiMovies.getMovieO(movieId)
        Mockito.verify(apiMovies).getMovieO(movieId)
        assertNotNull(request)
        assertEquals(responseMovie, request)
    }

    @Test
    fun getTvShow() = runBlockingTest {
        Mockito.`when`(apiMovies.getTvShowO(any())).thenReturn(responseTvShow)
        val request = apiMovies.getTvShowO(tvShowId)
        Mockito.verify(apiMovies).getTvShowO(tvShowId)
        assertNotNull(request)
        assertEquals(responseTvShow, request)
    }

}