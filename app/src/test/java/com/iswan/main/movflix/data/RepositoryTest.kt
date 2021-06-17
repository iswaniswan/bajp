package com.iswan.main.movflix.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import androidx.paging.PagingSource
import app.cash.turbine.test
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.data.source.local.LocalDataSource
import com.iswan.main.movflix.data.source.local.entity.MovieEntity
import com.iswan.main.movflix.data.source.local.entity.TvShowEntity
import com.iswan.main.movflix.data.source.remote.rest.ApiService
import com.iswan.main.movflix.data.vo.Resource
import com.iswan.main.movflix.utils.*
import com.iswan.main.movflix.utils.PagingDataUtil.collectDataForTest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class RepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val localDataSource: LocalDataSource = Mockito.mock(LocalDataSource::class.java)
    private val apiService: ApiService = Mockito.mock(ApiService::class.java)

    private val responseMovie = DummyResponses.generateResponseMovie()
    private val responseMovies = DummyResponses.generateResponseMovies()
    private val responseTvShow = DummyResponses.generateResponseTvShow()
    private val responseTvShows = DummyResponses.generateResponseTvShows()

    private val dummyMovies = DummyModels.generateMovies()
    private val dummyMovieDetail = DummyModels.generateMovieDetail()
    private val dummyMovieId = dummyMovieDetail.id.toString()
    private val dummyTvShows = DummyModels.generateTvShows()
    private val dummyTvShowDetail = DummyModels.generateTvShowDetail()
    private val dummyTvShowId = dummyTvShowDetail.id.toString()

    @Test
    fun getFavouriteMovies() = runBlockingTest {
        val pagingSource = mock(PagingSource::class.java) as PagingSource<Int, MovieEntity>
        Mockito.`when`(localDataSource.getFavouriteMovies()).thenReturn(pagingSource)
        localDataSource.getFavouriteMovies()
        Mockito.verify(localDataSource).getFavouriteMovies()

        val pagingMovies = flow {
            emit(PagingDataUtil.mockData(Movie::class.java))
        }
        pagingMovies.test {
            val result = expectItem().collectDataForTest()
            assertEquals(20, result.size)
            assertEquals(dummyMovies.get(0).id, result.get(0).id)
            expectComplete()
        }
    }

    @Test
    fun getMovies() = runBlockingTest {
        whenever(
            apiService.searchMovies(anyString(), anyInt(), anyString())
        ) doReturn DummyResponses.generateResponseMovies()

        apiService.searchMovies("123456", 1, "API_KEY")
        Mockito.verify(apiService).searchMovies(anyString(), anyInt(), anyString())

        whenever(
            apiService.getTrendingMovie(anyInt(), anyString())
        ) doReturn DummyResponses.generateResponseMovies()

        apiService.getTrendingMovie(1, "API_KEY")
        Mockito.verify(apiService).getTrendingMovie(anyInt(), anyString())

        val pagingMovies = flow {
            emit(PagingDataUtil.mockData(Movie::class.java))
        }
        pagingMovies.test {
            val result = expectItem().collectDataForTest()
            assertEquals(20, result.size)
            assertEquals(dummyMovies.get(0).id, result.get(0).id)
            expectComplete()
        }
    }

    @Test
    fun getMovie() = runBlocking {
        whenever(
            localDataSource.getFavouriteMovie(anyString())
        ) doReturn flowOf(DummyEntities.generateMovieEntities().first())

        localDataSource.getFavouriteMovie(anyString())
        Mockito.verify(localDataSource).getFavouriteMovie(anyString())

        val movieDetail = flow {
            emit(Resource.Success(DummyModels.generateMovieDetail()))
        }
        movieDetail.test {
            val result = expectItem().data
            assertEquals(dummyMovieDetail.id, result?.id)
            expectComplete()
        }
    }

    @Test
    fun getFavouriteTvShows()= runBlocking {
        val pagingSource = mock(PagingSource::class.java) as PagingSource<Int, TvShowEntity>
        Mockito.`when`(localDataSource.getFavouriteTvShow()).thenReturn(pagingSource)
        localDataSource.getFavouriteTvShow()
        Mockito.verify(localDataSource).getFavouriteTvShow()

        val pagingTvShow = flow {
            emit(PagingData.from(dummyTvShows))
        }
        pagingTvShow.test {
            val result = expectItem().collectDataForTest()
            assertEquals(20, result.size)
            assertEquals(dummyTvShows.get(0).id, result.get(0).id)
            expectComplete()
        }
    }

    @Test
    fun getTvShows() = runBlockingTest {
        whenever(
            apiService.searchTvShows(anyString(), anyInt(), anyString())
        ) doReturn DummyResponses.generateResponseTvShows()

        apiService.searchTvShows("123456", 1, "API_KEY")
        Mockito.verify(apiService).searchTvShows(anyString(), anyInt(), anyString())

        whenever(
            apiService.getTrendingTvShow(anyInt(), anyString())
        ) doReturn DummyResponses.generateResponseTvShows()

        apiService.getTrendingTvShow(1, "API_KEY")
        Mockito.verify(apiService).getTrendingTvShow(anyInt(), anyString())

        val pagingTvShow = flow {
            emit(PagingData.from(dummyTvShows))
        }
        pagingTvShow.test {
            val result = expectItem().collectDataForTest()
            assertEquals(20, result.size)
            assertEquals(dummyTvShows.get(0).id, result.get(0).id)
            expectComplete()
        }
    }

    @Test
    fun getTvShow() = runBlockingTest {
        whenever(localDataSource.getFavouriteTvShow(anyString())) doReturn flow {
            DummyEntities.generateTvShowEntities().first()
        }
        localDataSource.getFavouriteTvShow(anyString())
        Mockito.verify(localDataSource).getFavouriteTvShow(anyString())

        val tvShowDetail = flowOf(Resource.Success(DummyModels.generateTvShowDetail()))
        tvShowDetail.test {
            val result = expectItem().data
            assertEquals(dummyTvShowDetail.id, result?.id)
            expectComplete()
        }
    }

    @Test
    fun insertUpdateFavouriteMovie() {
        val dummyEntity = DummyEntities.generateMovieEntities().get(0)
        doNothing().`when`(localDataSource).insertUpdateFavouriteMovie(dummyEntity)
        localDataSource.insertUpdateFavouriteMovie(dummyEntity)
        Mockito.verify(localDataSource).insertUpdateFavouriteMovie(dummyEntity)
    }

    @Test
    fun insertUpdateFavouriteTvShow() {
        val dummyEntity = DummyEntities.generateTvShowEntities().get(0)
        doNothing().`when`(localDataSource).insertUpdateFavouriteTvShow(dummyEntity)
        localDataSource.insertUpdateFavouriteTvShow(dummyEntity)
        Mockito.verify(localDataSource).insertUpdateFavouriteTvShow(dummyEntity)
    }
}