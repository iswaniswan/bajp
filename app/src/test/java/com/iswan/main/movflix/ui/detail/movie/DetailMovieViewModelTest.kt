package com.iswan.main.movflix.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.data.models.Company
import com.iswan.main.movflix.data.models.Genre
import com.iswan.main.movflix.utils.DataDummy
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
    private val sampleMovieDetail = DataDummy.getSampleMovieDetail()

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun getMovieDetail() {
        val movie = viewModel.movie
        viewModel.getMovie(sampleMovieDetail.id.toString())
        Thread.sleep(2000)
        assertNotNull(movie)
        assertEquals(sampleMovieDetail.title, movie.value?.title)
        assertEquals(sampleMovieDetail.adult, movie.value?.adult)
        assertEquals(sampleMovieDetail.backdropPath, movie.value?.backdropPath)
        assertEquals(sampleMovieDetail.budget, movie.value?.budget)
        assertEquals(sampleMovieDetail.genres, movie.value?.genres)
        assertEquals(sampleMovieDetail.homepage, movie.value?.homepage)
        assertEquals(sampleMovieDetail.id, movie.value?.id)
        assertEquals(sampleMovieDetail.originalLanguage, movie.value?.originalLanguage)
        assertEquals(sampleMovieDetail.originalTitle, movie.value?.originalTitle)
        assertEquals(sampleMovieDetail.overview, movie.value?.overview)
        assertEquals(sampleMovieDetail.popularity, movie.value?.popularity)
        assertEquals(sampleMovieDetail.posterPath, movie.value?.posterPath)
        assertEquals(sampleMovieDetail.productionCompanies, movie.value?.productionCompanies)
        assertEquals(sampleMovieDetail.releaseDate, movie.value?.releaseDate)
        assertEquals(sampleMovieDetail.revenue, movie.value?.revenue)
        assertEquals(sampleMovieDetail.runtime, movie.value?.runtime)
        assertEquals(sampleMovieDetail.status, movie.value?.status)
        assertEquals(sampleMovieDetail.tagline, movie.value?.tagline)
        assertEquals(sampleMovieDetail.title, movie.value?.title)
        assertEquals(sampleMovieDetail.voteAverage, movie.value?.voteAverage)
        assertEquals(sampleMovieDetail.voteCount, movie.value?.voteCount)
    }

}
