@file:Suppress("LocalVariableName")
package com.iswan.main.movflix.ui.main.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.utils.LiveDataTestUtil
import com.iswan.main.movflix.utils.MainCoroutineRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
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
//class MoviesViewModelTest {
//
//    @get:Rule
//    val testRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val mainCoroutineRule = MainCoroutineRule()
//
//    private lateinit var viewModel: MoviesViewModel
//
//    @Mock
//    private lateinit var repository: Repository
//
//    @Mock
//    private lateinit var observer: Observer<ArrayList<Movie>>
//
//    @Test
//    fun getMovies() {
//        val dummyMovies = FakeData.generateMovies()
//        val _movies = MutableLiveData<ArrayList<Movie>>()
//        _movies.value = dummyMovies
//
//        runBlockingTest {
//            Mockito.`when`(repository.getMovies()).thenReturn(FakeData.generateMovies())
//            viewModel = MoviesViewModel(repository)
//            Mockito.verify(repository).getMovies()
//        }
//
//        val movies = LiveDataTestUtil.getValue(viewModel.listMovie)
//        assertNotNull(movies)
//        assertEquals(_movies.value, movies)
//        assertEquals(20, movies.size)
//
//        viewModel.listMovie.observeForever(observer)
//        Mockito.verify(observer).onChanged(dummyMovies)
//    }
//
//
//}