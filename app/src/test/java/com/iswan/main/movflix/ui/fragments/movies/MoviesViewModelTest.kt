package com.iswan.main.movflix.ui.fragments.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import app.cash.turbine.test
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.Movie
import com.iswan.main.movflix.utils.DummyModels
import com.iswan.main.movflix.utils.LiveDataTestUtil
import com.iswan.main.movflix.utils.MainCoroutineRule
import com.iswan.main.movflix.utils.PagingDataUtil.collectDataForTest
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.lenient
import org.mockito.junit.MockitoJUnitRunner
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val repository = Mockito.mock(Repository::class.java)
    private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    private val dummyMovies = DummyModels.generateMovies()

    private val dummyFlow = flowOf(PagingData.from(dummyMovies))
    private lateinit var viewModel: MoviesViewModel

    @Mock
    private lateinit var observer: Observer<PagingData<Movie>>

    @Before
    fun `handle asLiveData extension before viewModel initialization`() {
        whenever(repository.getFavouriteMovies()).thenReturn(dummyFlow)
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun search() = runBlockingTest {
        val _movies = MutableLiveData<PagingData<Movie>>()
        val movies: LiveData<PagingData<Movie>> = _movies

        Mockito.`when`(repository.getMovies(anyString())).thenReturn(dummyFlow)
        viewModel.search("123456")
        Mockito.verify(repository).getMovies("123456")

        Mockito.`when`(repository.getMovies("")).thenReturn(dummyFlow)
        viewModel.search("")
        Mockito.verify(repository).getMovies("")

        withContext(dispatcher) {
            dummyFlow.test {
                val result = expectItem()
                _movies.postValue(result)

                movies.observeForever(observer)
                Mockito.verify(observer).onChanged(result)

                val moviesTest = LiveDataTestUtil.getValue(movies).collectDataForTest()
                assertEquals(20, moviesTest.size)
                assertEquals(dummyMovies[0].id, moviesTest[0].id)

                expectComplete()
            }
        }
    }

    @Test
    fun getFavourites() = runBlockingTest {
        val _favourites = MutableLiveData<PagingData<Movie>>()
        val favourites : LiveData<PagingData<Movie>> = _favourites

        lenient().`when`(repository.getFavouriteMovies()).thenReturn(dummyFlow)
        Mockito.verify(repository).getFavouriteMovies()

        _favourites.value = PagingData.from(dummyMovies)

        withContext(dispatcher) {
            dummyFlow.test {
                val result = expectItem()
                _favourites.postValue(result)

                favourites.observeForever(observer)
                Mockito.verify(observer).onChanged(result)

                val moviesTest = LiveDataTestUtil.getValue(favourites).collectDataForTest()
                assertEquals(20, moviesTest.size)
                assertEquals(dummyMovies[0].id, moviesTest[0].id)

                expectComplete()
            }
        }
    }
}