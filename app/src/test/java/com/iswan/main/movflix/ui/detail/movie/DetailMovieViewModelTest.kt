@file:Suppress("LocalVariableName")
package com.iswan.main.movflix.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.iswan.main.movflix.data.FakeData
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.MovieDetail
import com.iswan.main.movflix.utils.LiveDataTestUtil
import com.iswan.main.movflix.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<MovieDetail>

    private lateinit var viewModel: DetailMovieViewModel
    private val fakeMovieDetail = FakeData.generateMovieDetail()
    private val movieId = fakeMovieDetail.id.toString()

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(repository)
    }

    @Test
    fun getMovieDetail() {
        val _movieDetail = MutableLiveData<MovieDetail>()
        _movieDetail.value = fakeMovieDetail

        runBlockingTest {
            Mockito.`when`(repository.getMovie(movieId)).thenReturn(fakeMovieDetail)
            viewModel.getMovie(movieId)
            Mockito.verify(repository).getMovie(movieId)
            val movieDetail = LiveDataTestUtil.getValue(viewModel.movie)
            assertNotNull(movieDetail)
            assertEquals(_movieDetail.value?.id, movieDetail.id)
            assertEquals(_movieDetail.value?.title, movieDetail.title)
            assertEquals(_movieDetail.value?.adult, movieDetail.adult)
            assertEquals(_movieDetail.value?.backdropPath, movieDetail.backdropPath)
            assertEquals(_movieDetail.value?.budget, movieDetail.budget)
            assertEquals(_movieDetail.value?.genres, movieDetail.genres)
            assertEquals(_movieDetail.value?.homepage, movieDetail.homepage)
            assertEquals(_movieDetail.value?.originalLanguage, movieDetail.originalLanguage)
            assertEquals(_movieDetail.value?.originalTitle, movieDetail.originalTitle)
            assertEquals(_movieDetail.value?.overview, movieDetail.overview)
            assertEquals(_movieDetail.value?.popularity, movieDetail.popularity)
            assertEquals(_movieDetail.value?.posterPath, movieDetail.posterPath)
            assertEquals(
                _movieDetail.value?.productionCompanies,
                movieDetail.productionCompanies
            )
            assertEquals(_movieDetail.value?.releaseDate, movieDetail.releaseDate)
            assertEquals(_movieDetail.value?.revenue, movieDetail.revenue)
            assertEquals(_movieDetail.value?.runtime, movieDetail.runtime)
            assertEquals(_movieDetail.value?.status, movieDetail.status)
            assertEquals(_movieDetail.value?.tagline, movieDetail.tagline)
            assertEquals(_movieDetail.value?.voteAverage, movieDetail.voteAverage)
            assertEquals(_movieDetail.value?.voteCount, movieDetail.voteCount)
        }
        viewModel.movie.observeForever(observer)
        Mockito.verify(observer).onChanged(fakeMovieDetail)
    }

}
