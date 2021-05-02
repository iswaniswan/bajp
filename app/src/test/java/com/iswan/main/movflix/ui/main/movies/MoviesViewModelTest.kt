package com.iswan.main.movflix.ui.main.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.utils.MainCoroutineRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setup() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movies = viewModel.listMovie
        Thread.sleep(2000)
        assertNotNull(movies)
        assertEquals(20, movies.value?.size)
    }


}