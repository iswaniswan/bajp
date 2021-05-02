package com.iswan.main.movflix.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailTvViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailTvViewModel
    private val ID: Int = 71712
    private val TITLE: String = "The Good Doctor"

    @Before
    fun setup() {
        viewModel = DetailTvViewModel()
    }

    @Test
    fun getMovies() {
        val tvShow = viewModel.tvShow
        viewModel.getTvShow(ID.toString())
        Thread.sleep(2000)
        assertNotNull(tvShow)
        assertEquals(TITLE, tvShow.value?.name)
    }
}