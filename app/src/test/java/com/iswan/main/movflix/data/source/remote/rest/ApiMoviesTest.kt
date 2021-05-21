package com.iswan.main.movflix.data.source.remote.rest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.data.FakeResponse
import com.iswan.main.movflix.utils.DataDummy
import com.iswan.main.movflix.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ApiMoviesTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var apiClient: ApiClient

    private val API_KEY = "API_KEY"

    private val movieId = DataDummy.getSampleMovieDetail().id.toString()
    private val tvShowId = DataDummy.getSampleTvShowDetail().id.toString()

    private val responseMovie = FakeResponse.generateResponseMovie()
    private val responseMovies = FakeResponse.generateResponseMovies()
    private val responseTvShow = FakeResponse.generateResponseTvShow()
    private val responseTvShows = FakeResponse.generateResponseTvShows()

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)

    @Test
    fun getTrendingMoviesO() {
        testScope.launch {
            val request = apiClient.instance().trendingMovieO(API_KEY)
            Mockito.`when`(request).thenReturn(responseMovies)
            Mockito.verify(apiClient.instance().trendingMovieO(API_KEY))
            assertNotNull(request)
            assertEquals(responseMovies, request)
            assertEquals(20, request.results.size)
        }
    }

    @Test
    fun getTrendingTvShowsO() {
        testScope.launch {
            val request = apiClient.instance().trendingTvShowO(API_KEY)
            Mockito.`when`(request).thenReturn(responseTvShows)
            Mockito.verify(apiClient.instance().trendingTvShowO(API_KEY))
            assertNotNull(request)
            assertEquals(responseTvShows, request)
            assertEquals(20, request.results.size)
        }
    }

    @Test
    fun getMovieO() {
        testScope.launch {
            val request = apiClient.instance().getMovie(movieId, API_KEY)
            Mockito.`when`(request).thenReturn(responseMovie)
            Mockito.verify(apiClient.instance().getMovie(movieId, API_KEY))
            assertNotNull(request)
            assertEquals(responseMovie, request)
        }
    }

    @Test
    fun getTvShowO() {
        testScope.launch {
            val request = apiClient.instance().getTvShow(tvShowId, API_KEY)
            Mockito.`when`(request).thenReturn(responseTvShow)
            Mockito.verify(apiClient.instance().getTvShow(tvShowId, API_KEY))
            assertNotNull(request)
            assertEquals(responseTvShow, request)
        }
    }

}