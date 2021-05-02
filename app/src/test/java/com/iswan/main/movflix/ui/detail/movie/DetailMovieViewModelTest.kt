package com.iswan.main.movflix.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailMovieViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailMovieViewModel
    private val id: Int = 460465
    private val title: String = "Mortal Kombat"

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getMovies() {
        val movie = viewModel.movie
        viewModel.getMovie(id.toString())
        Thread.sleep(2000)
        assertNotNull(movie)
        assertEquals(title, movie.value?.title)
    }

}
