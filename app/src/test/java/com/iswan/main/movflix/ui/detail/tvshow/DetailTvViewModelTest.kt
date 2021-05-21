@file:Suppress("LocalVariableName")
package com.iswan.main.movflix.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.iswan.main.movflix.data.FakeData
import com.iswan.main.movflix.data.Repository
import com.iswan.main.movflix.data.models.TvShowDetail
import com.iswan.main.movflix.utils.*
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
class DetailTvViewModelTest {

    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<TvShowDetail>

    private lateinit var viewModel: DetailTvViewModel
    private val fakeTvShowDetail = FakeData.generateTvShowDetail()
    private val tvShowId = fakeTvShowDetail.id.toString()

    @Before
    fun setup() {
        viewModel = DetailTvViewModel(repository)
    }

    @Test
    fun getTvShowsDetail() {
        val _tvShowDetail = MutableLiveData<TvShowDetail>()
        _tvShowDetail.value = fakeTvShowDetail

        runBlockingTest {
            Mockito.`when`(repository.getTvShow(tvShowId)).thenReturn(fakeTvShowDetail)
            viewModel.getTvShow(tvShowId)
            Mockito.verify(repository).getTvShow(tvShowId)
            val tvShowDetail = LiveDataTestUtil.getValue(viewModel.tvShow)
            assertNotNull(tvShowDetail)
            assertEquals(_tvShowDetail.value?.id, tvShowDetail.id)
            assertEquals(_tvShowDetail.value?.name, tvShowDetail.name)
            assertEquals(_tvShowDetail.value?.backdropPath, tvShowDetail.backdropPath)
            assertEquals(_tvShowDetail.value?.episodeRunTime, tvShowDetail.episodeRunTime)
            assertEquals(_tvShowDetail.value?.firstAirDate, tvShowDetail.firstAirDate)
            assertEquals(_tvShowDetail.value?.genres, tvShowDetail.genres)
            assertEquals(_tvShowDetail.value?.homepage, tvShowDetail.homepage)
            assertEquals(_tvShowDetail.value?.lastAirDate, tvShowDetail.lastAirDate)
            assertEquals(_tvShowDetail.value?.numberOfSeasons, tvShowDetail.numberOfSeasons)
            assertEquals(
                _tvShowDetail.value?.originalLanguage,
                tvShowDetail.originalLanguage
            )
            assertEquals(_tvShowDetail.value?.overview, tvShowDetail.overview)
            assertEquals(_tvShowDetail.value?.popularity, tvShowDetail.popularity)
            assertEquals(_tvShowDetail.value?.posterPath, tvShowDetail.posterPath)
            assertEquals(
                _tvShowDetail.value?.productionCompanies,
                tvShowDetail.productionCompanies
            )
            assertEquals(_tvShowDetail.value?.seasons, tvShowDetail.seasons)
            assertEquals(_tvShowDetail.value?.status, tvShowDetail.status)
            assertEquals(_tvShowDetail.value?.tagline, tvShowDetail.tagline)
            assertEquals(_tvShowDetail.value?.voteAverage, tvShowDetail.voteAverage)
            assertEquals(_tvShowDetail.value?.voteCount, tvShowDetail.voteCount)
        }
        viewModel.tvShow.observeForever(observer)
        Mockito.verify(observer).onChanged(fakeTvShowDetail)
    }
}