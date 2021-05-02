package com.iswan.main.movflix.ui.main.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.utils.MainCoroutineRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TvShowsViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TvShowsViewModel

    @Before
    fun setup() {
        viewModel = TvShowsViewModel()
    }

    @Test
    fun getMovies() {
        val tvShows = viewModel.listMovie
        Thread.sleep(2000)
        assertNotNull(tvShows)
        assertEquals(20, tvShows.value?.size)
    }


}