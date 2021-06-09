@file:Suppress("LocalVariableName")
package com.iswan.main.movflix.ui.main.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.utils.LiveDataTestUtil
import com.iswan.main.movflix.utils.MainCoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

//@ExperimentalCoroutinesApi
//@RunWith(MockitoJUnitRunner::class)
//class TvShowsViewModelTest {
//
//    @get:Rule
//    val testRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val mainCoroutineRule = MainCoroutineRule()
//
//    @Mock
//    private lateinit var repository: Repository
//
//    @Mock
//    private lateinit var observer: Observer<ArrayList<TvShow>>
//
//    private lateinit var viewModel: TvShowsViewModel
//
//    @Test
//    fun getTvShows() {
//        val dummyTvShows = FakeData.generateTvShows()
//        val _tvShows = MutableLiveData<ArrayList<TvShow>>()
//        _tvShows.value = dummyTvShows
//
//        runBlockingTest {
//            Mockito.`when`(repository.getTvShows()).thenReturn(FakeData.generateTvShows())
//            viewModel = TvShowsViewModel(repository)
//            Mockito.verify(repository).getTvShows()
//        }
//
//        val tvShows = LiveDataTestUtil.getValue(viewModel.listMovie)
//        assertEquals(_tvShows.value, tvShows)
//        assertEquals(20, tvShows.size)
//
//        viewModel.listMovie.observeForever(observer)
//        Mockito.verify(observer).onChanged(dummyTvShows)
//    }
//
//}