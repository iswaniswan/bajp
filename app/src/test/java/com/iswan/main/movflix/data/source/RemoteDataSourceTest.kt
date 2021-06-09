package com.iswan.main.movflix.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.data.FakeResponse
import com.iswan.main.movflix.data.source.remote.rest.ApiResponse
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

//@ExperimentalCoroutinesApi
//@RunWith(MockitoJUnitRunner::class)
//class RemoteDataSourceTest {
//
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val mainCoroutineRule = MainCoroutineRule()
//
//    @Mock
//    private lateinit var apiResponse: ApiResponse
//
//    private val movieId = DataDummy.getSampleMovieDetail().id.toString()
//    private val tvShowId = DataDummy.getSampleTvShowDetail().id.toString()
//
//    private val responseMovie = FakeResponse.generateResponseMovie()
//    private val responseMovies = FakeResponse.generateResponseMovies()
//    private val responseTvShow = FakeResponse.generateResponseTvShow()
//    private val responseTvShows = FakeResponse.generateResponseTvShows()
//
//    @Test
//    fun getMovies() = runBlockingTest {
//        Mockito.`when`(apiResponse.getTrendingMoviesO()).thenReturn(responseMovies)
//        val request = apiResponse.getTrendingMoviesO()
//        Mockito.verify(apiResponse).getTrendingMoviesO()
//        assertNotNull(request)
//        assertEquals(responseMovies, request)
//        assertEquals(20, request.results.size)
//    }
//
//    @Test
//    fun getTvShows() = runBlockingTest {
//        Mockito.`when`(apiResponse.getTrendingTvShowsO()).thenReturn(responseTvShows)
//        val request = apiResponse.getTrendingTvShowsO()
//        Mockito.verify(apiResponse).getTrendingTvShowsO()
//        assertNotNull(request)
//        assertEquals(responseTvShows, request)
//        assertEquals(20, request.results.size)
//    }
//
//    @Test
//    fun getMovie() = runBlockingTest {
//        Mockito.`when`(apiResponse.getMovieO(any())).thenReturn(responseMovie)
//        val request = apiResponse.getMovieO(movieId)
//        Mockito.verify(apiResponse).getMovieO(movieId)
//        assertNotNull(request)
//        assertEquals(responseMovie, request)
//    }
//
//    @Test
//    fun getTvShow() = runBlockingTest {
//        Mockito.`when`(apiResponse.getTvShowO(any())).thenReturn(responseTvShow)
//        val request = apiResponse.getTvShowO(tvShowId)
//        Mockito.verify(apiResponse).getTvShowO(tvShowId)
//        assertNotNull(request)
//        assertEquals(responseTvShow, request)
//    }
//
//}