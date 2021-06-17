package com.iswan.main.movflix.ui.fragments.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import app.cash.turbine.test
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.TvShow
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
class TvShowsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    private val dispatcher: CoroutineDispatcher = TestCoroutineDispatcher()
    private val dummyTvShows = DummyModels.generateTvShows()

    private val dummyFlow = flowOf(PagingData.from(dummyTvShows))
    private lateinit var viewModel: TvShowsViewModel

    @Mock
    private lateinit var observer: Observer<PagingData<TvShow>>

    @Before
    fun `handle asLiveData extension before viewModel initialization`() {
        whenever(repository.getFavouriteTvShows()).thenReturn(dummyFlow)
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun search() = runBlockingTest {
        val _tvShows = MutableLiveData<PagingData<TvShow>>()
        val tvShows: LiveData<PagingData<TvShow>> = _tvShows

        Mockito.`when`(repository.getTvShows(anyString())).thenReturn(dummyFlow)
        viewModel.search("123456")
        Mockito.verify(repository).getTvShows("123456")

        Mockito.`when`(repository.getTvShows("")).thenReturn(dummyFlow)
        viewModel.search("")
        Mockito.verify(repository).getTvShows("")

        withContext(dispatcher) {
            dummyFlow.test {
                val result = expectItem()
                _tvShows.postValue(result)

                tvShows.observeForever(observer)
                Mockito.verify(observer).onChanged(result)

                val tvShowsTest = LiveDataTestUtil.getValue(tvShows).collectDataForTest()
                assertEquals(20, tvShowsTest.size)
                assertEquals(dummyTvShows[0].id, tvShowsTest[0].id)

                expectComplete()
            }
        }
    }

    @Test
    fun getFavourites() = runBlockingTest {
        val _favourites = MutableLiveData<PagingData<TvShow>>()
        val favourites : LiveData<PagingData<TvShow>> = _favourites

        lenient().`when`(repository.getFavouriteTvShows()).thenReturn(dummyFlow)
        Mockito.verify(repository).getFavouriteTvShows()

        _favourites.value = PagingData.from(dummyTvShows)

        withContext(dispatcher) {
            dummyFlow.test {
                val result = expectItem()
                _favourites.postValue(result)

                favourites.observeForever(observer)
                Mockito.verify(observer).onChanged(result)

                val moviesTest = LiveDataTestUtil.getValue(favourites).collectDataForTest()
                assertEquals(20, moviesTest.size)
                assertEquals(dummyTvShows[0].id, moviesTest[0].id)

                expectComplete()
            }
        }
    }
}