package com.iswan.main.movflix.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iswan.main.movflix.utils.DataDummy
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
    private val sampleTvShowDetail = DataDummy.getSampleTvShowDetail()

    @Before
    fun setup() {
        viewModel = DetailTvViewModel()
    }

    @Test
    fun getTvShowsDetail() {
        val tvShow = viewModel.tvShow
        viewModel.getTvShow(sampleTvShowDetail.id.toString())
        Thread.sleep(2000)
        assertNotNull(tvShow)
        assertEquals(sampleTvShowDetail.backdropPath, tvShow.value?.backdropPath)
        assertEquals(sampleTvShowDetail.episodeRunTime, tvShow.value?.episodeRunTime)
        assertEquals(sampleTvShowDetail.firstAirDate, tvShow.value?.firstAirDate)
        assertEquals(sampleTvShowDetail.genres, tvShow.value?.genres)
        assertEquals(sampleTvShowDetail.homepage, tvShow.value?.homepage)
        assertEquals(sampleTvShowDetail.id, tvShow.value?.id)
        assertEquals(sampleTvShowDetail.lastAirDate, tvShow.value?.lastAirDate)
        assertEquals(sampleTvShowDetail.name, tvShow.value?.name)
        assertEquals(sampleTvShowDetail.numberOfSeasons, tvShow.value?.numberOfSeasons)
        assertEquals(sampleTvShowDetail.originalLanguage, tvShow.value?.originalLanguage)
        assertEquals(sampleTvShowDetail.overview, tvShow.value?.overview)
        assertEquals(sampleTvShowDetail.popularity, tvShow.value?.popularity)
        assertEquals(sampleTvShowDetail.posterPath, tvShow.value?.posterPath)
        assertEquals(sampleTvShowDetail.productionCompanies, tvShow.value?.productionCompanies)
        assertEquals(sampleTvShowDetail.seasons, tvShow.value?.seasons)
        assertEquals(sampleTvShowDetail.status, tvShow.value?.status)
        assertEquals(sampleTvShowDetail.tagline, tvShow.value?.tagline)
        assertEquals(sampleTvShowDetail.voteAverage, tvShow.value?.voteAverage)
        assertEquals(sampleTvShowDetail.voteCount, tvShow.value?.voteCount)
    }
}